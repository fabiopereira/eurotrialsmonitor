/* Copyright 2004 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.acegitech.core.sitemesh;

import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.mapper.PathMapper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;


/**
 * Maps decorators to requests based on URI requests.
 * 
 * <P>
 * Usually setup by the {@link ContextConfigLoaderEditor} from a properties
 * file.
 * </p>
 * 
 * <p>
 * This class is used by {@link ContextDecoratorMapper}, and uses {@link
 * PathMapper} for pattern matching.
 * </p>
 *
 * @author Ben Alex
 * @version $Id$
 */
public class ContextConfigLoader {
    //~ Instance fields ========================================================

    private Map decorators = new HashMap();
    private PathMapper pathMapper = new PathMapper();

    //~ Methods ================================================================

    public Decorator getDecoratorByName(String name) throws ServletException {
        return (Decorator) decorators.get(name);
    }

    public String getMappedName(String path) throws ServletException {
        return pathMapper.get(path);
    }

    public void addDecorator(Decorator d) {
        if (d.getRole() != null) {
            decorators.put(d.getName() + d.getRole(), d);
        } else {
            decorators.put(d.getName(), d);
        }
    }

    public void putInPathMapper(String name, String pattern) {
        this.pathMapper.put(name, pattern);
    }
}
