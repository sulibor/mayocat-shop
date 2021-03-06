(function (global)
{
    if (typeof out === "undefined") {
        // Can be useful for Q&D debugging
        // usage: out['println(java.lang.String)']("Hello " + something);
        var out = java.lang.System.out;
    }

    // Polyfills -------------------------------------------------------------------------------------------------------

    Object.extend = function (destination, source) {
        for (var property in source) {
            if (source[property] && source[property].constructor &&
                source[property].constructor === Object)
            {
                destination[property] = destination[property] || {};
                arguments.callee(destination[property], source[property]);
            } else {
                destination[property] = source[property];
            }
        }
        return destination;
    };

    // Helpers ---------------------------------------------------------------------------------------------------------

    var getObjectPropertyFromPath = function (obj, path) {
        var props = path.split(".");
        return props.reduce(function(memo, prop) {
            return memo && memo[prop];
        }, obj);
    };

    var getComponent = function (clazz, hint) {
        hint = typeof hint === "undefined" ? "default" : hint;
        return org.mayocat.util.Utils.getComponent(clazz, hint);
    };

    // Java bindings ---------------------------------------------------------------------------------------------------

    var ThemeManager = org.mayocat.theme.ThemeManager,
        Breakpoint = Packages.org.mayocat.theme.Breakpoint;

    // Handlears helpers -----------------------------------------------------------------------------------------------

    Handlebars.registerHelper('include', function (template, options) {
        var partial = Handlebars.partials[template];
        var context = Object.extend(Object.extend({}, this), options.hash);
        if (typeof partial === "function") {
            return new Handlebars.SafeString(partial(context));
        }
        return "Partial not found : " + template;
    });

    Handlebars.registerHelper('templateSource', function (template, options) {
        var themeManager = getComponent(ThemeManager);
        var resolved = themeManager.getTemplate(template, Breakpoint.DEFAULT);

        return String(resolved.getContent());
    });

    Handlebars.registerHelper('includeTemplate', function (template, options) {
        var themeManager = getComponent(ThemeManager);
        var resolved = themeManager.getTemplate(template, Breakpoint.DEFAULT);

        var name = resolved.getId(),
            content = String(resolved.getContent());

        global.templates[name] = Handlebars.compile(content, {noEscape: true});
        Handlebars.registerPartial(name, global.templates[name]);

        var partial = Handlebars.partials[resolved.getId()];
        var context = Object.extend(Object.extend({}, this), options.hash);
        if (typeof partial === "function") {
            return new Handlebars.SafeString(partial(context));
        }
        return "Partial not found : [" + template + "] with id [" + resolved.getId() + "]";
    });

    Handlebars.registerHelper('resource', function (path, options) {
        return "/resources/" + path;
    });

    Handlebars.registerHelper('isPath', function (path, options) {
        if (typeof this.location !== "undefined") {
            if (path === this.location.path) {
                return options.fn(this);
            }
        }
        return options.inverse(this);
    });

    Handlebars.registerHelper('addon', function (path, options) {
        // Defaults
        var sources = ["theme", "platform"],
            valueType = "display";

        // Options
        if (typeof options.hash["type"] !== "undefined") {
            valueType = options.hash["type"]
        }

        if (typeof options.hash["source"] !== "undefined") {
            sources = [ options.hash["source"] ];
        }

        for (var i = 0; i < sources.length; i++) {
            var source = sources[i],
                addon = getObjectPropertyFromPath(this[source + "_addons"], path);
            if (typeof addon !== "undefined" && addon !== null) {
                return addon[valueType];
            }
            // else continue looking in next source of addons
        }
        // surrender when there is no source left
        return undefined;
    });

})(this);