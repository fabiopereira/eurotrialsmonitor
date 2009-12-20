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

import com.opensymphony.module.sitemesh.mapper.DefaultDecorator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.HashMap;
import java.util.Map;


/**
 * Property editor to assist with the setup of a {@link ContextConfigLoader}.
 * 
 * <P>
 * Expects entries to be in the form:
 * </p>
 * 
 * <P>
 * <code>DEFAULT_PATH=/decorators<BR>decorator_name,page=pattern,[pattern]<BR>decorator_name,page=pattern,[pattern]
 * </code>
 * </p>
 * 
 * <P>
 * The <code>DEFAULT_PATH</code> (case sensitive) must be specified and should
 * be specified as the first line.
 * </p>
 * 
 * <P>
 * The resulting <code>ContextConfigLoader</code> will lack some optional
 * advanced configuration. Specifically, the webapp, roles and decorator
 * parameters are not set by this property editor. These settings are not
 * required in typical deployments and can be added if circumstances require.
 * </p>
 *
 * @author Ben Alex
 * @version $Id$
 */
public class ContextConfigLoaderEditor extends PropertyEditorSupport {
    //~ Static fields/initializers =============================================

    private static final Log logger = LogFactory.getLog(ContextConfigLoaderEditor.class);

    //~ Methods ================================================================

    public void setAsText(String s) throws IllegalArgumentException {
        ContextConfigLoader source = new ContextConfigLoader();

        if ((s == null) || "".equals(s)) {
            // Leave target object empty
        } else {
            BufferedReader br = new BufferedReader(new StringReader(s));
            int counter = 0;
            String line;

            String defaultPath = "";

            while (true) {
                counter++;

                try {
                    line = br.readLine();
                } catch (IOException ioe) {
                    throw new IllegalArgumentException(ioe.getMessage());
                }

                if (line == null) {
                    break;
                }

                line = line.trim();

                if (logger.isDebugEnabled()) {
                    logger.debug("Line " + counter + ": " + line);
                }

                if (line.startsWith("//")) {
                    continue;
                }

                if (line.lastIndexOf('=') == -1) {
                    continue;
                }

                // Tokenize the line into its name/value tokens
                String[] lineValue = StringUtils.delimitedListToStringArray(line,
                        "=");
                String name = lineValue[0];
                String value = lineValue[1];

                // Handle the DEFAULT_PATH special case
                if ("DEFAULT_PATH".equals(name)) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Default path now being used: " + value);
                    }

                    defaultPath = value;

                    continue;
                }

                // Tokenize the name into its name/page_name tokens
                String[] nameValue = StringUtils
                    .commaDelimitedListToStringArray(name);
                String decoratorName = nameValue[0];
                String page = defaultPath + "/" + nameValue[1];

                // Work out the remaining decorator values to use
                String uriPath = null;
                String role = null;
                Map params = new HashMap();

                // Add the decorator mapping
                source.addDecorator(new DefaultDecorator(decoratorName, page,
                        uriPath, role, params));

                // Add each patterns mapping
                String[] patterns = StringUtils.commaDelimitedListToStringArray(value);

                for (int i = 0; i < patterns.length; i++) {
                    source.putInPathMapper(decoratorName, patterns[i]);
                }
            }
        }

        setValue(source);
    }
}
