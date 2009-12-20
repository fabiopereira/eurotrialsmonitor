/* Copyright 2004 Acegi Technology Pty Limited. ALL RIGHTS RESERVED.
 *
 * PROPRIETARY/CONFIDENTIAL. UNAUTHORIZED USE STRICTLY PROHIBITED. 
 */

package com.acegitech.core.sitemesh;

import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.AbstractDecoratorMapper;

import org.springframework.beans.factory.InitializingBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


/**
 * Application context aware implementation of {@link DecoratorMapper}.
 * 
 * <P>
 * Maps decorators to patterns based on the {@link ContextConfigLoader}
 * configuration.
 * </p>
 * 
 * <P>
 * You can use this class directly in your application context. You should
 * <B>not</B> wrap it with a {@link DecoratorMapperFactory}.
 * </p>
 *
 * @author Ben Alex
 * @version $Id$
 */
public class ContextDecoratorMapper extends AbstractDecoratorMapper
    implements InitializingBean {
    //~ Instance fields ========================================================

    // Must name variable "parent" to maintain reference with superclass.
    protected DecoratorMapper parent = null;
    private ContextConfigLoader contextConfigLoader;

    //~ Methods ================================================================

    public void setContextConfigLoader(ContextConfigLoader configLoader) {
        this.contextConfigLoader = configLoader;
    }

    public ContextConfigLoader getContextConfigLoader() {
        return contextConfigLoader;
    }

    /**
     * Retrieve {@link com.opensymphony.module.sitemesh.Decorator} based on
     * "pattern" tag.
     *
     * @param request DOCUMENT ME!
     * @param page DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Decorator getDecorator(HttpServletRequest request, Page page) {
        String thisPath = request.getServletPath();

        // getServletPath() returns null unless the mapping corresponds to a servlet
        if (thisPath == null) {
            String requestURI = request.getRequestURI();

            if (request.getPathInfo() != null) {
                // strip the pathInfo from the requestURI
                thisPath = requestURI.substring(0,
                        requestURI.indexOf(request.getPathInfo()));
            } else {
                thisPath = requestURI;
            }
        }

        String name = null;

        try {
            name = contextConfigLoader.getMappedName(thisPath);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        Decorator result = getNamedDecorator(request, name);

        return (result == null) ? super.getDecorator(request, page) : result;
    }

    /**
     * Retrieve Decorator named in "name" attribute. Checks the role if
     * specified.
     *
     * @param request DOCUMENT ME!
     * @param name DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Decorator getNamedDecorator(HttpServletRequest request, String name) {
        Decorator result = null;

        try {
            result = contextConfigLoader.getDecoratorByName(name);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        if ((result == null)
            || ((result.getRole() != null)
            && !request.isUserInRole(result.getRole()))) {
            // if the result is null or the user is not in the role
            return super.getNamedDecorator(request, name);
        } else {
            return result;
        }
    }

    public void setParentMapper(DecoratorMapper parent) {
        this.parent = parent;
    }

    public DecoratorMapper getParentMapper() {
        return this.parent;
    }

    public void afterPropertiesSet() throws Exception {
        if (this.contextConfigLoader == null) {
            throw new IllegalArgumentException(
                "ContextConfigLoader is required");
        }
    }
}
