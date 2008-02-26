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
package org.seasar.extension.jdbc.gen.generator;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;

import javax.persistence.TemporalType;

import org.junit.Test;
import org.seasar.extension.jdbc.gen.javacode.AbstractJavaCode;
import org.seasar.extension.jdbc.gen.javacode.EntityBaseJavaCode;
import org.seasar.extension.jdbc.gen.javacode.EntityJavaCode;
import org.seasar.extension.jdbc.gen.model.EntityModel;
import org.seasar.extension.jdbc.gen.model.PropertyModel;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.TextUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import static org.junit.Assert.*;

/**
 * @author taedium
 * 
 */
public class JavaFileGeneratorImplTest {

    private JavaFileGeneratorImpl generator;

    private Writer writer;

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGenerate() throws Exception {
        String packageName = ClassUtil.getPackageName(getClass());
        File file = ResourceUtil.getResourceAsFile(packageName
                .replace('.', '/'));
        Configuration cfg = new Configuration();
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDirectoryForTemplateLoading(file);
        writer = new StringWriter();
        generator = new JavaFileGeneratorImpl(cfg, null, "UTF-8") {

            @Override
            protected boolean exists(File file) {
                return false;
            }

            @Override
            protected void makeDirsIfNecessary(File dir) {
            }

            @Override
            protected Writer openWriter(File file) {
                return writer;
            }
        };
        generator.generate(new MyJavaCode("hoge.Foo", getClass()
                .getSimpleName()
                + "_hoge.ftl"));
        assertEquals("hoge", writer.toString());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGenerateEntityCode() throws Exception {
        File file = ResourceUtil.getResourceAsFile("templates");
        Configuration cfg = new Configuration();
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDirectoryForTemplateLoading(file);
        writer = new StringWriter();
        generator = new JavaFileGeneratorImpl(cfg, null, "UTF-8") {

            @Override
            protected boolean exists(File file) {
                return false;
            }

            @Override
            protected void makeDirsIfNecessary(File dir) {
            }

            @Override
            protected Writer openWriter(File file) {
                return writer;
            }
        };
        EntityModel model = new EntityModel();
        EntityJavaCode code = new EntityJavaCode(model, "hoge.Foo",
                "bar.AbstractFoo", "entity.ftl");
        generator.generate(code);
        String path = getClass().getName().replace(".", "/") + "_entity.txt";
        assertEquals(TextUtil.readUTF8(path), writer.toString());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGenerateEntityBase() throws Exception {
        File file = ResourceUtil.getResourceAsFile("templates");
        Configuration cfg = new Configuration();
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDirectoryForTemplateLoading(file);
        writer = new StringWriter();
        generator = new JavaFileGeneratorImpl(cfg, null, "UTF-8") {

            @Override
            protected boolean exists(File file) {
                return false;
            }

            @Override
            protected void makeDirsIfNecessary(File dir) {
            }

            @Override
            protected Writer openWriter(File file) {
                return writer;
            }
        };

        PropertyModel id = new PropertyModel();
        id.setName("id");
        id.setId(true);
        id.setPropertyClass(int.class);

        PropertyModel name = new PropertyModel();
        name.setName("name");
        name.setPropertyClass(String.class);

        PropertyModel lob = new PropertyModel();
        lob.setName("lob");
        lob.setLob(true);
        lob.setPropertyClass(byte[].class);

        PropertyModel date = new PropertyModel();
        date.setName("date");
        date.setTemporalType(TemporalType.DATE);
        date.setPropertyClass(java.util.Date.class);

        PropertyModel temp = new PropertyModel();
        temp.setName("temp");
        temp.setTransient(true);
        temp.setPropertyClass(String.class);

        PropertyModel version = new PropertyModel();
        version.setName("version");
        version.setVersion(true);
        version.setPropertyClass(Integer.class);

        EntityModel model = new EntityModel();
        model.setName("Foo");
        model.addPropertyModel(id);
        model.addPropertyModel(name);
        model.addPropertyModel(lob);
        model.addPropertyModel(date);
        model.addPropertyModel(temp);
        model.addPropertyModel(version);

        EntityBaseJavaCode code = new EntityBaseJavaCode(model,
                "bar.AbstractFoo", "entityBase.ftl");
        generator.generate(code);
        String path = getClass().getName().replace(".", "/")
                + "_entityBase.txt";
        assertEquals(TextUtil.readUTF8(path), writer.toString());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGenerateEntityBase_compositeId() throws Exception {
        File file = ResourceUtil.getResourceAsFile("templates");
        Configuration cfg = new Configuration();
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDirectoryForTemplateLoading(file);
        writer = new StringWriter();
        generator = new JavaFileGeneratorImpl(cfg, null, "UTF-8") {

            @Override
            protected boolean exists(File file) {
                return false;
            }

            @Override
            protected void makeDirsIfNecessary(File dir) {
            }

            @Override
            protected Writer openWriter(File file) {
                return writer;
            }
        };

        PropertyModel id1 = new PropertyModel();
        id1.setName("id1");
        id1.setId(true);
        id1.setPropertyClass(int.class);

        PropertyModel id2 = new PropertyModel();
        id2.setName("id2");
        id2.setId(true);
        id2.setPropertyClass(int.class);

        EntityModel model = new EntityModel();
        model.setName("Foo");
        model.addPropertyModel(id1);
        model.addPropertyModel(id2);

        EntityBaseJavaCode code = new EntityBaseJavaCode(model,
                "bar.AbstractFoo", "entityBase.ftl");
        generator.generate(code);
        String path = getClass().getName().replace(".", "/")
                + "_entityBase_compositeId.txt";
        assertEquals(TextUtil.readUTF8(path), writer.toString());
    }

    /**
     * 
     * @author taedium
     * 
     */
    public static class MyJavaCode extends AbstractJavaCode {

        /**
         * 
         * @param className
         * @param templateName
         */
        public MyJavaCode(String className, String templateName) {
            super(className, templateName);
        }

        public String getBaseClassName() {
            return null;
        }

        public Set<String> getImportPackageNames() {
            return null;
        }

        public String getShortBaseClassName() {
            return null;
        }

    }
}