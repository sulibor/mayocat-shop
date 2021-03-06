package org.mayocat.accounts.resources;

import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mayocat.authorization.annotation.Authorized;
import org.mayocat.context.Execution;
import org.mayocat.rest.annotation.ExistingTenant;
import org.mayocat.rest.Resource;
import org.mayocat.configuration.ConfigurationService;
import org.mayocat.configuration.NoSuchModuleException;
import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;

import com.yammer.metrics.annotation.Timed;

@Component(ConfigurationResource.PATH)
@Path(ConfigurationResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authorized
public class ConfigurationResource implements Resource
{
    public static final String PATH = API_ROOT_PATH + "configuration";

    @Inject
    private Logger logger;

    @Inject
    private ConfigurationService configurationService;

    @Inject
    private Execution execution;

    @GET
    @Timed
    @Path("settings")
    @ExistingTenant
    public Map<String, Object> getConfiguration()
    {
        return configurationService.getSettingsAsJson();
    }

    @GET
    @Timed
    @Path("settings/{module}")
    @ExistingTenant
    public Map<String, Object> getModuleConfiguration(@PathParam("module") String module)
    {
        try {
            return configurationService.getSettingsAsJson(module);
        } catch (NoSuchModuleException e) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No such module could be found\n").type(MediaType.TEXT_PLAIN_TYPE).build());
        }
    }

    @PUT
    @Timed
    @Path("settings/{module}")
    @Authorized //(roles = Role.ADMIN)
    @ExistingTenant
    public Response updateModuleConfiguration(@PathParam("module") String module, Map<String, Object> configuration)
    {
        try {
            configurationService.updateSettings(module, configuration);
            return Response.noContent().build();
        }
        catch (NoSuchModuleException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No such module could be found\n").type(MediaType.TEXT_PLAIN_TYPE).build();
        }
    }

    @PUT
    @Timed
    @Authorized //(roles = Role.ADMIN)
    @Path("settings")
    @ExistingTenant
    public Response updateModuleConfiguration(Map<String, Object> configuration)
    {
        configurationService.updateSettings(configuration);
        return Response.noContent().build();
    }

    @GET
    @Timed
    @Path("gestalt")
    public Map<String, Object> getGestaltConfiguration()
    {
        return configurationService.getGestaltConfiguration();
    }
}
