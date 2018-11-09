package medestin;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import medestin.health.TemplateHealthCheck;
import medestin.resources.ProjectResource;

public class DropwizardProjectApplication extends Application<DropwizardProjectConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardProjectApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardProject";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardProjectConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardProjectConfiguration configuration,
                    final Environment environment) {
        final ProjectResource resource = new ProjectResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
