package com.soft.appservice2.poi;


import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import java.io.Serializable;

/**
 * excel,csv 操作
 */
public class Operation {


    public static FlatFileItemReader<Person> csvItemReader() {
        FlatFileItemReader<Person> csvItemReader = new FlatFileItemReader<>();
        csvItemReader.setResource(new ClassPathResource("data/all.csv"));
        csvItemReader.setLinesToSkip(1);
        csvItemReader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"name", "age"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return csvItemReader;
    }


    public static void main(String[]args){

        try {
            FlatFileItemReader<Person> flatFileItemReader = csvItemReader();
            flatFileItemReader.open(new ExecutionContext());
            Person person = flatFileItemReader.read();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}

class PersonFieldSetMapper implements FieldSetMapper{

    @Override
    public Object mapFieldSet(FieldSet fieldSet) throws BindException {

        Person person = new Person();
        person.setName(fieldSet.readString(0));
        person.setAge(fieldSet.readString(1));
        return person;
    }
}


class Person implements Serializable{
    String name;//
    String age;//

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
