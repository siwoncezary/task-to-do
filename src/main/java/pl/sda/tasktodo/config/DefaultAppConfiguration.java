package pl.sda.tasktodo.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("default")
public class DefaultAppConfiguration implements IAppConfiguration{
    @Override
    public int getPageSize() {
        return 5;
    }

    @Override
    public int getPageStart() {
        return 1;
    }

    @Override
    public String getTableColor() {
        return "table-secondary";
    }

    @Override
    public String getPassword() {
        return "";
    }
}
