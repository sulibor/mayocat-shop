package org.mayocat.shop.configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.mayocat.configuration.AbstractSettings;
import org.mayocat.configuration.general.GeneralSettings;
import org.mayocat.configuration.theme.ThemeSettings;
import org.mayocat.shop.catalog.configuration.shop.CatalogSettings;
import org.mayocat.shop.checkout.CheckoutSettings;
import org.mayocat.shop.shipping.configuration.ShippingSettings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.db.DatabaseConfiguration;

/**
 * @version $Id$
 */
public class MayocatShopSettings extends AbstractSettings
{
    @Valid
    @NotNull
    @JsonProperty
    private DatabaseConfiguration database = new DatabaseConfiguration();

    @Valid
    @NotNull
    @JsonProperty
    private GeneralSettings general = new GeneralSettings();

    @Valid
    @NotNull
    @JsonProperty
    private ThemeSettings theme = new ThemeSettings();

    @Valid
    @NotNull
    @JsonProperty
    private CheckoutSettings checkout = new CheckoutSettings();

    @Valid
    @NotNull
    @JsonProperty
    private CatalogSettings catalog = new CatalogSettings();

    @Valid
    @NotNull
    @JsonProperty
    private ShippingSettings shipping = new ShippingSettings();

    public DatabaseConfiguration getDatabaseConfiguration()
    {
        return database;
    }

    public GeneralSettings getGeneralSettings()
    {
        return general;
    }

    public CatalogSettings getCatalogSettings()
    {
        return catalog;
    }

    public ThemeSettings getThemeSettings()
    {
        return theme;
    }

    public CheckoutSettings getCheckoutSettings()
    {
        return checkout;
    }

    public ShippingSettings getShippingSettings()
    {
        return shipping;
    }
}
