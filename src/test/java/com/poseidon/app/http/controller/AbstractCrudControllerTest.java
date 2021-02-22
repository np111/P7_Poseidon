package com.poseidon.app.http.controller;

import com.poseidon.app.service.CrudService;
import com.poseidon.app.util.AuthMock.WithUserAuth;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.poseidon.app.util.CsrfTokenTestRepository.csrf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public abstract class AbstractCrudControllerTest<Service extends CrudService<Model, Entity, ID>, Model, Entity, ID> extends AbstractControllerTest {
    protected final String prefix;
    protected final Class<Model> modelClass;
    protected final Class<ID> idClass;
    private Method modelGetIdMethod;

    public AbstractCrudControllerTest(String prefix, Class<Model> modelClass, Class<ID> idClass) {
        this.prefix = prefix;
        this.modelClass = modelClass;
        this.idClass = idClass;
        initReflection();
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

    protected abstract Service getService();

    protected abstract Model getModelA();

    protected abstract Model getModelB();

    @SneakyThrows
    protected MockHttpServletRequestBuilder appendFormData(MockHttpServletRequestBuilder request, Model model) {
        BeanInfo beanInfo = Introspector.getBeanInfo(modelClass);
        for (PropertyDescriptor prop : beanInfo.getPropertyDescriptors()) {
            Object value = prop.getReadMethod().invoke(model);
            if (value != null) {
                request.param(prop.getName(), value.toString());
            }
        }
        return request;
    }

    @Test
    @WithUserAuth
    void list() throws Exception {
        Service service = getService();
        Model modelA = getModelA();
        Model modelB = getModelB();
        when(service.list()).thenReturn(Arrays.asList(modelA, modelB));

        toMatchSnapshot(
                mockMvc.perform(get(prefix + "/list")).andReturn()
        );
    }

    @Test
    @WithUserAuth
    void add() throws Exception {
        toMatchSnapshot(
                mockMvc.perform(get(prefix + "/add")).andReturn()
        );
    }

    @Test
    @WithUserAuth
    void validate() throws Exception {
        Model modelA = getModelA();

        toMatchSnapshot(
                mockMvc.perform(post(prefix + "/validate").with(csrf())).andReturn(),
                mockMvc.perform(appendFormData(post(prefix + "/validate"), modelA).with(csrf())).andReturn()
        );
    }

    @Test
    @WithUserAuth
    void showUpdate() throws Exception {
        Service service = getService();
        Model modelA = getModelA();
        Model modelB = getModelB();
        when(service.read(getModelId(modelA))).thenThrow(new CrudService.EntityNotFoundException(getModelId(modelA)));
        when(service.read(getModelId(modelB))).thenReturn(modelB);

        toMatchSnapshot(
                mockMvc.perform(get(prefix + "/update/" + getModelId(modelA))).andReturn(),
                mockMvc.perform(get(prefix + "/update/" + getModelId(modelB))).andReturn()
        );
    }

    @Test
    @WithUserAuth
    void update() throws Exception {
        Model modelA = getModelA();

        toMatchSnapshot(
                mockMvc.perform(post(prefix + "/update/" + getModelId(modelA)).with(csrf())).andReturn(),
                mockMvc.perform(appendFormData(post(prefix + "/update/" + getModelId(modelA)), modelA).with(csrf())).andReturn()
        );
    }

    @Test
    @WithUserAuth
    void delete() throws Exception {
        Service service = getService();
        Model modelA = getModelA();
        Model modelB = getModelB();
        doThrow(new CrudService.EntityNotFoundException(getModelId(modelA))).when(service).delete(getModelId(modelA));

        toMatchSnapshot(
                mockMvc.perform(get(prefix + "/delete/" + getModelId(modelA))).andReturn(),
                mockMvc.perform(get(prefix + "/delete/" + getModelId(modelB))).andReturn()
        );
    }
}
