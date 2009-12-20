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
import com.opensymphony.module.sitemesh.Factory;
import com.opensymphony.module.sitemesh.PageParser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;

import java.util.Map;


/**
 * Enables a Spring application context to provide a SiteMesh {@link Factory}.
 * 
 * <P>
 * This is particularly useful when a decorator needs to reference other beans,
 * such as a WebDAV repository access collaborator.
 * </p>
 * 
 * <P>
 * To ensure the Sitemesh filter uses this factory instead of its default, set
 * the <code>ServletContext</code> attribute identified by {@link
 * Factory#SITEMESH_FACTORY} to this object. The  {@link SpringSitemeshFilter}
 * will handle this for you automatically.
 * </p>
 *
 * @author Ben Alex
 * @version $Id$
 */
public class SitemeshFactory extends Factory implements InitializingBean {
    //~ Static fields/initializers =============================================

    private static final Log logger = LogFactory.getLog(SitemeshFactory.class);

    //~ Instance fields ========================================================

    private DecoratorMapper decoratorMapper = null;
    private Map pageParsers = null;
    private PageParser defaultPageParser = null;

    //~ Methods ================================================================

    /**
     * Allows you to set the last <code>DecoratorMapper</code>. This object
     * will be used to locate the relevant decorator. If the object doesn't
     * specify a decorator, the object will delegate to its parent and so on.
     * You need to configure the chain in the application context, as
     * <code>SitemeshFactory</code> is only interested in the <B>last</B>
     * member of the chain.
     *
     * @param decoratorMapper The decoratorMapper to set
     */
    public void setDecoratorMapper(DecoratorMapper decoratorMapper) {
        this.decoratorMapper = decoratorMapper;
    }

    /**
     * Specified by {@link Factory#getDecoratorMapper()}, this method returns
     * the last mapper in the chain.
     *
     * @return the last decorator mapper in the chain
     */
    public DecoratorMapper getDecoratorMapper() {
        if (logger.isDebugEnabled()) {
            logger.debug("Returning decoratorMapper: " + this.decoratorMapper);
        }

        return this.decoratorMapper;
    }

    public void setDefaultPageParser(PageParser defaultPageParser) {
        this.defaultPageParser = defaultPageParser;
    }

    public PageParser getDefaultPageParser() {
        if (logger.isDebugEnabled()) {
            logger.debug("Returning defaultPageParser: "
                + this.defaultPageParser);
        }

        return defaultPageParser;
    }

    public PageParser getPageParser(String contentType) {
        if (logger.isDebugEnabled()) {
            logger.debug("Request to getPageParser for contextType: "
                + contentType);
        }

        if (pageParsers.containsKey(contentType)) {
            return (PageParser) pageParsers.get(contentType);
        } else {
            return defaultPageParser;
        }
    }

    public void setPageParsers(Map pageParsers) {
        this.pageParsers = pageParsers;
    }

    public Map getPageParsers() {
        return pageParsers;
    }

    public void afterPropertiesSet() throws Exception {
        if (this.decoratorMapper == null) {
            throw new IllegalArgumentException("A decoratorMapping is required");
        }

        if (this.defaultPageParser == null) {
            throw new IllegalArgumentException(
                "A default PageParser is required");
        }

        if (this.pageParsers == null) {
            throw new IllegalArgumentException(
                "A Map of PageParsers is required");
        }
    }

    public boolean shouldParsePage(String contentType) {
        return pageParsers.containsKey(contentType);
    }
}
