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

import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * Puts a {@link SitemeshFactory} into an application attribute.
 * 
 * <P>
 * The application attribute used is equal to that defined by {@link
 * com.opensymphony.module.sitemesh.Factory#SITEMESH_FACTORY}. The
 * <code>SitemeshFactory</code> will then be used by the Sitemesh
 * <code>PageFilter</code>.
 * </p>
 * 
 * <P>
 * Modelled as a filter so we can reuse {@link
 * net.sf.acegisecurity.util.FilterToBeanProxy} to access the actual filter
 * from a bean context.
 * </p>
 * 
 * <P>
 * <B>Do not use this class directly.</B> Instead configure
 * <code>web.xml</code> to use the {@link
 * net.sf.acegisecurity.util.FilterToBeanProxy}.
 * </p>
 *
 * @author Ben Alex
 * @version $Id$
 */
public class SpringSitemeshFilter implements InitializingBean, Filter {
    //~ Instance fields ========================================================

    private SitemeshFactory sitemeshFactory;

    //~ Methods ================================================================

    public void setSitemeshFactory(SitemeshFactory sitemeshFactory) {
        this.sitemeshFactory = sitemeshFactory;
    }

    public SitemeshFactory getSitemeshFactory() {
        return sitemeshFactory;
    }

    public void afterPropertiesSet() throws Exception {
        if (sitemeshFactory == null) {
            throw new IllegalArgumentException(
                "sitemeshFactory must be specified");
        }
    }

    public void contextInitialized(ServletContextEvent event) {}

    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fc) throws ServletException {
        fc.getServletContext().setAttribute("sitemesh.factory",
            this.sitemeshFactory);
    }
}
