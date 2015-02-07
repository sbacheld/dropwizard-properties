# Dropwizard Properties Service

## Installing

    git clone https://github.com/sbacheld/dropwizard-properties
    cd dropwizard-properties
    mvn install
    
After installing, add the following to your pom.xml

    <dependency>
        <groupId>me.seanbachelder.dropwizard</groupId>
        <artifactId>dropwizard-properties</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>

## Activating in Dropwizard

    import me.seanbachelder.dropwizard.PropertiesBundle;
    
    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        bootstrap.addBundle(new PropertiesBundle());
    }
