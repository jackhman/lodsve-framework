$(function () {
    var springfox = {
        "baseUrl": function () {
            var urlMatches = /(.*)\/swagger-ui.html.*/.exec(window.location.href);
            return urlMatches[1];
        },
        "uiConfig": function (cb) {
            $.getJSON(this.baseUrl() + "/configuration/ui", function (data) {
                cb(data);
            });
        }
    };
    window.springfox = springfox;
    window.oAuthRedirectUrl = springfox.baseUrl() + '/webjars/springfox-swagger-ui/o2c.html';

    window.springfox.uiConfig(function (data) {
        window.swaggerUi = new SwaggerUi({
            dom_id: "swagger-ui-container",
            validatorUrl: data.validatorUrl,
            supportedSubmitMethods: ['get', 'post', 'put', 'delete', 'patch'],
            onComplete: function (swaggerApi, swaggerUi) {
                // 设置网页标题
                document.title = swaggerApi.info.title

                if (window.SwaggerTranslator) {
                    window.SwaggerTranslator.translate();
                }

                $('pre code').each(function (i, e) {
                    hljs.highlightBlock(e)
                });

                $("input#input_apiKey").remove();
                $("a#explore").remove();

                $("div#api_info div.info_title").remove();
                $("div#api_info div.info_license").remove();
                $("div.footer").remove();

            },
            onFailure: function (data) {
                log("Unable to Load SwaggerUI");
            },
            docExpansion: data.docExpansion || 'none',
            jsonEditor: data.jsonEditor || false,
            apisSorter: data.apisSorter || 'alpha',
            defaultModelRendering: data.defaultModelRendering || 'schema',
            showRequestHeaders: data.showRequestHeaders || true
        });

        initializeBaseUrl();

        function addApiKeyAuthorization() {
            var key = encodeURIComponent($('#input_apiKey')[0].value);
            if (key && key.trim() != "") {
                var apiKeyAuth = new SwaggerClient.ApiKeyAuthorization(window.apiKeyName, key, window.apiKeyVehicle);
                window.swaggerUi.api.clientAuthorizations.add(window.apiKeyName, apiKeyAuth);
                log("added key " + key);
            }
        }

        $('#input_apiKey').change(addApiKeyAuthorization);

        function log() {
            if ('console' in window) {
                console.log.apply(console, arguments);
            }
        }
    });

    $('#select_baseUrl').change(function () {
        window.swaggerUi.headerView.trigger('update-swagger-ui', {
            url: $('#select_baseUrl').val()
        });
    });

    function maybePrefix(location, withRelativePath) {
        var pat = /^https?:\/\//i;
        if (pat.test(location)) {
            return location;
        }
        return withRelativePath + location;
    }

    function initializeBaseUrl() {
        var relativeLocation = springfox.baseUrl();

        $('#input_baseUrl').hide();

        $.getJSON(relativeLocation + "/swagger-resources", function (data) {
            var $urlDropdown = $('#select_baseUrl');
            $urlDropdown.empty();
            $.each(data, function (i, resource) {
                var option = $('<option></option>')
                    .attr("value", maybePrefix(resource.location, relativeLocation))
                    .text(resource.name + " (" + resource.location + ")");
                $urlDropdown.append(option);
            });
            $urlDropdown.change();
        });

    }
});


