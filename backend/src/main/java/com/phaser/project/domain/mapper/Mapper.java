package com.phaser.project.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    private static final org.modelmapper.ModelMapper modelMapper;
    static {
        modelMapper = new org.modelmapper.ModelMapper();
    }
    public static <T> T map(Object source, Class<T> destinationType){
        return modelMapper.map(source, destinationType);
    }

    public static void map(Object source, Object destination){
        modelMapper.map(source, destination);
    }
    public static  <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
