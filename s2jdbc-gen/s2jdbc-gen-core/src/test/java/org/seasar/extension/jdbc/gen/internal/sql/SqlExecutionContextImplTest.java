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
package org.seasar.extension.jdbc.gen.internal.sql;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Test;
import org.seasar.extension.jdbc.gen.internal.exception.SqlFailedException;
import org.seasar.extension.jdbc.gen.internal.sql.SqlExecutionContextImpl;
import org.seasar.framework.mock.sql.MockDataSource;

import static org.junit.Assert.*;

/**
 * @author taedium
 * 
 */
public class SqlExecutionContextImplTest {

    /**
     * 
     */
    @Test
    public void testGetStatement() {
        SqlExecutionContextImpl context = new SqlExecutionContextImpl(
                new MockDataSource(), false);
        Statement statement = context.getStatement();
        assertNotNull(statement);
        assertSame(statement, context.getStatement());
        context.addException(new SqlFailedException(new SQLException(), "aaa",
                1, "bbb"));
        assertNotSame(statement, context.getStatement());
    }

    /**
     * 
     */
    @Test
    public void testAddException() {
        SqlExecutionContextImpl context = new SqlExecutionContextImpl(
                new MockDataSource(), false);
        assertTrue(context.getExceptionList().isEmpty());
        SqlFailedException exception = new SqlFailedException(
                new SQLException(), "aaa", 1, "bbb");
        context.addException(exception);
        List<RuntimeException> list = context.getExceptionList();
        assertEquals(1, list.size());
    }

    /**
     * 
     */
    @Test
    public void testAddException_haltOnError() {
        SqlExecutionContextImpl context = new SqlExecutionContextImpl(
                new MockDataSource(), true);
        assertTrue(context.getExceptionList().isEmpty());
        SqlFailedException exception = new SqlFailedException(
                new SQLException(), "aaa", 1, "bbb");
        try {
            context.addException(exception);
            fail();
        } catch (SqlFailedException expected) {
        }
    }

    /**
     * 
     */
    @Test
    public void testDestroy() {
        SqlExecutionContextImpl context = new SqlExecutionContextImpl(
                new MockDataSource(), false);
        assertNotNull(context.getStatement());
        assertNotNull(context.connection);
        assertNotNull(context.statement);
        context.destroy();
        assertNull(context.connection);
        assertNull(context.statement);
    }

}