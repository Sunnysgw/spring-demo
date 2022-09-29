package com.sgw.config;

import com.sgw.constants.DatasourceConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatabaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.read")
    @Qualifier("readDataSource")
    public DataSource readDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.write")
    @Qualifier("writeDataSource")
    public DataSource writeDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean
    @Primary
    @DependsOn(value = {"writeDataSource", "readDataSource"})
    public DataSource dataSource(@Qualifier("readDataSource") DataSource readDataSource, @Qualifier("writeDataSource") DataSource writeDataSource) {
        Map<Object, Object> datasourceMap = new HashMap<>();
        datasourceMap.put(DatasourceConstants.READ_SOURCE, readDataSource);
        datasourceMap.put(DatasourceConstants.WRITE_SOURCE, writeDataSource);
        return new RoutingDataSource(writeDataSource, datasourceMap);
    }

    private static class RoutingDataSource extends AbstractRoutingDataSource{

        /**
         * Determine the current lookup key. This will typically be
         * implemented to check a thread-bound transaction context.
         * <p>Allows for arbitrary keys. The returned key needs
         * to match the stored lookup key type, as resolved by the
         * {@link #resolveSpecifiedLookupKey} method.
         */
        @Override
        protected Object determineCurrentLookupKey() {
            return DatasourceHolder.getKey();
        }

        public RoutingDataSource(DataSource defaultDatasource, Map<Object, Object> datasourceMap) {
            super.setDefaultTargetDataSource(defaultDatasource);
            super.setTargetDataSources(datasourceMap);
        }

    }

    public static class DatasourceHolder {
        private static final ThreadLocal<String> DATASOURCE_KEY = new ThreadLocal<>();

        public static void setKey(String key) {
            DATASOURCE_KEY.set(key);
        }

        public static String getKey() {
            return DATASOURCE_KEY.get();
        }

        public static void clearKey() {
            DATASOURCE_KEY.remove();
        }
    }

}
