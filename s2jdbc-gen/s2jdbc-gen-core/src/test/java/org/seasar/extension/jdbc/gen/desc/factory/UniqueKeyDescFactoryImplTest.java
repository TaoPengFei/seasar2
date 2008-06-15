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
package org.seasar.extension.jdbc.gen.desc.factory;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.junit.Before;
import org.junit.Test;
import org.seasar.extension.jdbc.gen.desc.ColumnDesc;
import org.seasar.extension.jdbc.gen.desc.UniqueKeyDesc;
import org.seasar.extension.jdbc.gen.desc.factory.UniqueKeyDescFactoryImpl;

import static org.junit.Assert.*;

/**
 * @author taedium
 * 
 */
public class UniqueKeyDescFactoryImplTest {

    private UniqueKeyDescFactoryImpl factory;

    @Before
    public void setUp() throws Exception {
        factory = new UniqueKeyDescFactoryImpl();
    }

    @Test
    public void testGetCompositeUniqueKey() throws Exception {
        UniqueConstraint[] uniqueConstraints = Aaa.class.getAnnotation(
                Table.class).uniqueConstraints();

        UniqueKeyDesc uniqueKeyDesc = factory
                .getCompositeUniqueKey(uniqueConstraints[0]);
        assertNotNull(uniqueKeyDesc);
        assertEquals(2, uniqueKeyDesc.getColumnNameList().size());
        assertEquals("aaa", uniqueKeyDesc.getColumnNameList().get(0));
        assertEquals("bbb", uniqueKeyDesc.getColumnNameList().get(1));

        uniqueKeyDesc = factory.getCompositeUniqueKey(uniqueConstraints[1]);
        assertNull(uniqueKeyDesc);
    }

    @Test
    public void testGetSingleUniqueKey() throws Exception {
        ColumnDesc columnDesc = new ColumnDesc();
        columnDesc.setName("aaa");
        columnDesc.setUnique(true);
        UniqueKeyDesc uniqueKeyDesc = factory.getSingleUniqueKey(columnDesc);
        assertNotNull(uniqueKeyDesc);
        assertEquals(1, uniqueKeyDesc.getColumnNameList().size());
        assertEquals("aaa", uniqueKeyDesc.getColumnNameList().get(0));

        columnDesc.setUnique(false);
        uniqueKeyDesc = factory.getSingleUniqueKey(columnDesc);
        assertNull(uniqueKeyDesc);
    }

    @Table(uniqueConstraints = {
            @UniqueConstraint(columnNames = { "aaa", "bbb" }),
            @UniqueConstraint(columnNames = {}) })
    public static class Aaa {
    }

}
