/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.extension.jdbc.gen.command;

import java.io.File;

import org.junit.After;
import org.junit.Test;
import org.seasar.extension.jdbc.gen.ServiceModel;
import org.seasar.extension.jdbc.gen.exception.RequiredPropertyNullRuntimeException;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import static org.junit.Assert.*;

/**
 * @author taedium
 * 
 */
public class GenerateServiceCommandTest {

    /**
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        SingletonS2ContainerFactory.destroy();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testValidate() throws Exception {
        GenerateServiceCommand command = new GenerateServiceCommand();
        command.setConfigPath("s2jdbc-gen-core-test.dicon");
        try {
            command.validate();
            fail();
        } catch (RequiredPropertyNullRuntimeException expected) {
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testFactoryMethod() throws Exception {
        GenerateServiceCommand command = new GenerateServiceCommand();
        command.setConfigPath("s2jdbc-gen-core-test.dicon");
        command.setClasspathRootDir(new File("dir"));
        command.validate();
        command.init();
        assertNotNull(command.createEntityMetaReader());
        assertNotNull(command.createServiceModelFactory());
        assertNotNull(command.createGenerator());
        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setPackageName("aaa");
        serviceModel.setShortClassName("bbb");
        assertNotNull(command.createGenerationContext(serviceModel, "ccc"));
    }
}
