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

import com.opensymphony.module.sitemesh.DecoratorMapper;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Properties;


/**
 * Creates Sitemesh <code>DecoratorMapper</code>s in a more Spring-friendly
 * way.
 * 
 * <P>
 * This factory does not present the created decorators with a
 * <code>Config</code> object. This means those decorators that rely on the
 * <code>Config</code> object will not work through this factory bean. In such
 * cases you'll need to write a Spring-specific decorator to deliver the same
 * functionality, such as {@link ContextDecoratorMapper}.
 * </p>
 *
 * @author Ben Alex
 * @version $Id$
 */
public class DecoratorMapperFactory implements FactoryBean, InitializingBean {
    //~ Instance fields ========================================================

    private Class targetClass;
    private DecoratorMapper parentMapper;
    private Properties properties = new Properties();

    //~ Methods ================================================================

    public Object getObject()
        throws InstantiationException, IllegalAccessException {
        DecoratorMapper decoratorMapper = (DecoratorMapper) targetClass
            .newInstance();
        decoratorMapper.init(null, properties, parentMapper);

        return decoratorMapper;
    }

    public Class getObjectType() {
        return targetClass;
    }

    public void setParentMapper(DecoratorMapper parentMapper) {
        this.parentMapper = parentMapper;
    }

    public DecoratorMapper getParentMapper() {
        return parentMapper;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public boolean isSingleton() {
        return true;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void afterPropertiesSet() throws Exception {
        if (this.targetClass == null) {
            throw new IllegalArgumentException("targetClass not specified!");
        }

        if (this.properties == null) {
            throw new IllegalArgumentException("properties not specified!");
        }
    }
}
