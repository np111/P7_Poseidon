package com.poseidon.app.service;

import com.poseidon.app.service.CrudService.EntityNotFoundException;
import com.poseidon.app.util.PojoUtil;
import java.lang.reflect.Method;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.TimeZone;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

public abstract class AbstractCrudServiceTest<Service extends CrudService<Model, Entity, ID>, Model, Entity, ID> implements ApplicationContextAware {
    protected final Class<Service> serviceClass;
    protected final Class<Model> modelClass;
    protected final Class<Entity> entityClass;
    protected final Class<ID> idClass;
    private Method modelGetIdMethod;
    protected Service service;

    @MockBean
    protected TimeService timeService;

    public AbstractCrudServiceTest(Class<Service> serviceClass, Class<Model> modelClass, Class<Entity> entityClass, Class<ID> idClass) {
        this.serviceClass = serviceClass;
        this.modelClass = modelClass;
        this.entityClass = entityClass;
        this.idClass = idClass;
        initReflection();
    }

    @BeforeEach
    private void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        when(timeService.nowSecs()).thenReturn(ZonedDateTime.of(2020, 2, 22, 10, 11, 12, 0, ZoneId.of("UTC")));
    }

    @SneakyThrows
    private void initReflection() {
        modelGetIdMethod = modelClass.getDeclaredMethod("getId");
        assertEquals(idClass, modelGetIdMethod.getReturnType());
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    private ID getModelId(Model model) {
        return (ID) modelGetIdMethod.invoke(model);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.service = ctx.getBean(serviceClass);
    }

    protected void prepareCreateAssertion(Model excepted) {
    }

    protected void prepareUpdateAssertion(Model excepted, Model current) {
    }

    @Test
    @SneakyThrows
    void setModelId() {
        Model model = modelClass.getConstructor().newInstance();
        ID exceptedId = getUnreachableTestId();

        // Make sure that the initial ID is different from the one expected at the end.
        ID initialId = getModelId(model);
        assertNotEquals(exceptedId, initialId);

        // Assign the expected ID and and verifies that it worked.
        service.setModelId(model, exceptedId);
        ID newId = getModelId(model);
        assertEquals(exceptedId, newId);
    }

    @Test
    void crudOperations() {
        service.getRepository().deleteAll();

        // Create
        Model modelA = assertCreate(PojoUtil.getPodam().manufacturePojoWithFullData(modelClass));
        Model modelB = assertCreate(PojoUtil.getPodam().manufacturePojoWithFullData(modelClass));

        // Read + List (following create)
        assertRead(getModelId(modelA), modelA);
        assertRead(getModelId(modelB), modelB);
        assertReadThrowsNotFound(getUnreachableTestId());
        assertList(modelA, modelB);

        // Update
        modelA = assertUpdate(getModelId(modelA), PojoUtil.getPodam().manufacturePojoWithFullData(modelClass));
        assertUpdateThrowsNotFound(getUnreachableTestId(), PojoUtil.getPodam().manufacturePojoWithFullData(modelClass));

        // Read + List (following update)
        assertRead(getModelId(modelA), modelA);
        assertRead(getModelId(modelB), modelB);
        assertReadThrowsNotFound(getUnreachableTestId());
        assertList(modelA, modelB);

        // Delete
        assertDelete(getModelId(modelA));
        assertDeleteThrowsNotFound(getUnreachableTestId());

        // Read + List (following delete)
        assertReadThrowsNotFound(getModelId(modelA));
        assertRead(getModelId(modelB), modelB);
        assertReadThrowsNotFound(getUnreachableTestId());
        assertList(modelB);
    }

    private Model assertCreate(Model excepted) {
        // Clone excepted data
        Model newModel = PojoUtil.deepClone(excepted);

        // Update excepted (to match the business logic)
        prepareCreateAssertion(excepted);

        // Create
        Model actual = service.create(newModel);
        service.setModelId(excepted, getModelId(actual));

        // Assert and returns
        assertEquals(excepted, actual);
        return actual;
    }

    private void assertRead(ID actualId, Model excepted) {
        Model actual = service.read(actualId);
        assertEquals(excepted, actual);
    }

    private void assertReadThrowsNotFound(ID notFoundId) {
        try {
            service.read(notFoundId);
            fail("Excepted EntityNotFoundException");
        } catch (EntityNotFoundException ex) {
            assertEquals(notFoundId, ex.getEntityId());
        }
    }

    @SafeVarargs
    private final void assertList(Model... excepted) {
        List<Model> actual = service.list();
        assertArrayEquals(excepted, actual.toArray());
    }

    private Model assertUpdate(ID actualId, Model excepted) {
        // Clone excepted data
        Model newModel = PojoUtil.deepClone(excepted);

        // Update excepted (to match the business logic)
        Model current = null;
        try {
            current = service.read(actualId);
        } catch (EntityNotFoundException ignored) {
        }
        prepareUpdateAssertion(excepted, current);

        // Update
        Model actual = service.update(actualId, newModel);
        service.setModelId(excepted, actualId);

        // Assert and returns
        assertEquals(excepted, actual);
        return actual;
    }

    private void assertUpdateThrowsNotFound(ID notFoundId, Model newModel) {
        try {
            service.update(notFoundId, newModel);
            fail("Excepted EntityNotFoundException");
        } catch (EntityNotFoundException ex) {
            assertEquals(notFoundId, ex.getEntityId());
        }
    }

    private void assertDelete(ID actualId) {
        service.delete(actualId);
        // assert: not thrown
    }

    private void assertDeleteThrowsNotFound(ID notFoundId) {
        try {
            service.delete(notFoundId);
            fail("Excepted EntityNotFoundException");
        } catch (EntityNotFoundException ex) {
            assertEquals(notFoundId, ex.getEntityId());
        }
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    private ID getUnreachableTestId() {
        if (Number.class.isAssignableFrom(idClass)) {
            return (ID) idClass.getDeclaredMethod("valueOf", String.class).invoke(null, "33333");
        }
        throw new UnsupportedOperationException("Unsupported ID class: " + idClass);
    }
}
