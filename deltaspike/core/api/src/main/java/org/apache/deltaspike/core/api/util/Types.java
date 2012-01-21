/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.deltaspike.core.api.util;

import javax.enterprise.inject.Typed;
import java.lang.reflect.Type;

/**
 * Utilities for Types.
 */
@Typed()
public class Types
{
    private Types()
    {
    }

    /**
     * Gets the boxed type of a class
     *
     * @param type The type
     * @return The boxed type
     */
    public static Type boxedType(Type type)
    {
        if (type instanceof Class<?>)
        {
            return boxedClass((Class<?>) type);
        }
        else
        {
            return type;
        }
    }

    public static Class<?> boxedClass(Class<?> type)
    {
        if (!type.isPrimitive())
        {
            return type;
        }
        else if (type.equals(Boolean.TYPE))
        {
            return Boolean.class;
        }
        else if (type.equals(Character.TYPE))
        {
            return Character.class;
        }
        else if (type.equals(Byte.TYPE))
        {
            return Byte.class;
        }
        else if (type.equals(Short.TYPE))
        {
            return Short.class;
        }
        else if (type.equals(Integer.TYPE))
        {
            return Integer.class;
        }
        else if (type.equals(Long.TYPE))
        {
            return Long.class;
        }
        else if (type.equals(Float.TYPE))
        {
            return Float.class;
        }
        else if (type.equals(Double.TYPE))
        {
            return Double.class;
        }
        else if (type.equals(Void.TYPE))
        {
            return Void.class;
        }
        else
        {
            // Vagaries of if/else statement, can't be reached ;-)
            return type;
        }
    }
}
