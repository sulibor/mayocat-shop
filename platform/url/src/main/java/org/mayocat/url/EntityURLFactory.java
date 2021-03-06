package org.mayocat.url;

import java.net.URL;

import org.mayocat.accounts.model.Tenant;
import org.mayocat.model.Entity;
import org.xwiki.component.annotation.Role;

/**
 * Use this component to create URL for entities.
 *
 * @version $Id$
 */
@Role
public interface EntityURLFactory<E extends Entity>
{
    /**
     * Creates the URL of a certain type (API or public) for a given entity, belonging to a given tenant
     *
     * @param entity the entity to create the URL for
     * @param tenant the tenant the entity belongs to
     * @param type the type of URL to create. For example public, or API URL
     * @return the URL corresponding to this entity and tenant
     */
    URL create(E entity, Tenant tenant, URLType type);

    /**
     * Same as {@link #create(org.mayocat.model.Entity, org.mayocat.accounts.model.Tenant, URLType)} with the {@link
     * URLType#PUBLIC} type of URL.
     *
     * @param entity the entity to create the URL for
     * @param tenant the tenant the entity belongs to
     * @return the public URL corresponding to this entity and tenant
     */
    URL create(E entity, Tenant tenant);
}
