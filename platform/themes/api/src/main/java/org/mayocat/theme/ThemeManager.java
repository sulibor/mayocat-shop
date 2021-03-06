package org.mayocat.theme;

import org.mayocat.views.Template;
import org.xwiki.component.annotation.Role;

import com.google.common.base.Optional;

/**
 * @version $Id$
 */
@Role
public interface ThemeManager
{
    /**
     * Get the index template for a given breakpoint. The index template is the equivalent of "index.html", it's the
     * root template of the theme.
     *
     * @param breakpoint the breakpoint to get the index template for (none, or mobile, or tablet, etc.)
     * @return the index template
     * @throws TemplateNotFoundException when the index template has not been found
     */
    Template getIndexTemplate(Breakpoint breakpoint) throws TemplateNotFoundException;

    /**
     * Gets the template with the given name for a given breakpoint.
     *
     * @param name the name of the template to get. For example "product.html.tpl" or "user.mustache", etc.
     * @param breakpoint the breakpoint to get the template for (none, or mobile, or tablet, etc.)
     * @return the found template
     * @throws TemplateNotFoundException when the template is not found
     */
    Template getTemplate(String name, Breakpoint breakpoint) throws TemplateNotFoundException;

    /**
     * Gets a resource (an image, a font, a CSS file, the theme.yml file, etc.) with a given name and for a given
     * breakpoint.
     *
     * @param name the name of the resource to get
     * @param breakpoint the breakpoint to get the resource for
     * @return the found resource, or {@code null} when the resource is not found.
     */
    ThemeResource getResource(String name, Breakpoint breakpoint);

    /**
     * Resolves the path of a model with a given id
     *
     * @param id the id of the model to resolve the path for
     * @return an option of a path, present with the model template's path if found, absent otherwise.
     */
    Optional<String> resolveModelPath(String id);
}
