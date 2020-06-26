package com.mateo9x.webapp.converters;


import com.mateo9x.webapp.commands.DistributorCommand;
import com.mateo9x.webapp.model.Distributor;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DistributorCommandToDistributor implements Converter<DistributorCommand, Distributor> {


    @Synchronized
    @Nullable
    @Override
    public Distributor convert(DistributorCommand source){
        if (source == null){
            return null;
        }
        final Distributor distributor = new Distributor();
        distributor.setName(source.getName());
        return distributor;
    }
}
