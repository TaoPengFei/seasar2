/*
 * Copyright 2004-2015 the Seasar Foundation and the Others.
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
package org.seasar.framework.container.factory;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.unit.S2FrameworkTestCase;

/**
 * @author higa
 */
public class ComponentTagHandler2Test extends S2FrameworkTestCase {

    private S2Container child;

    public void setUp() throws Exception {
        include("ComponentTagHandler2Test.dicon");
    }

    /**
     * @throws Exception
     */
    public void testNormal() throws Exception {
        Foo foo = (Foo) child.getComponent("foo");
        assertNotNull("1", foo);
        assertEquals("2", "111", foo.getAaa());
        Foo foo2 = (Foo) child.getComponent("foo2");
        assertNotNull("3", foo);
        assertEquals("4", "222", foo2.getAaa());
    }

    /**
     * @throws Exception
     */
    public void testInterfaceWithAspect() throws Exception {
        Greeting greeting = (Greeting) child.getComponent(Greeting.class);
        assertEquals("1", "Hello", greeting.greet());
    }
}