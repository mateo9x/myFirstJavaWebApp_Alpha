package com.mateo9x.webapp.converters;

import com.mateo9x.webapp.commands.DirectorCommand;
import com.mateo9x.webapp.model.Director;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DirectorCommandtoDirector implements Converter<DirectorCommand, Director> {

@Synchronized
    @Nullable
    @Override
    public Director convert(DirectorCommand source) {
    if (source == null) {
        return null;
    }

    final Director director = new Director();
    director.setFirstName(source.getFirstName());
    director.setLastName(source.getLastName());
    director.setPseudo(source.getPseudo());

    return director;
}

}
