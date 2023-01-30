package com.example.search.pojo.dto;

import com.example.search.pojo.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonListDTO {
    private List<Person> personList;
}
