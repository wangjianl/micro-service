package com.soft.appservice2.poi;


import com.google.common.collect.Lists;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import java.io.Serializable;
import java.util.List;

/**
 * excel,csv 操作
 */
public class Operation {

    public static List<CsvModules> allList = Lists.newArrayList();
    public static List<CsvModules> servenList = Lists.newArrayList();

    public static FlatFileItemReader<CsvModules> csvItemReader4All() {
        FlatFileItemReader<CsvModules> csvItemReader = new FlatFileItemReader<>();
        csvItemReader.setResource(new ClassPathResource("data/all.csv"));
        csvItemReader.setEncoding("GBK");
        csvItemReader.setLinesToSkip(2);
        csvItemReader.setLineMapper(new DefaultLineMapper<CsvModules>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S",
                        "T","U","V","W","X","Y","Z","AA","AB","AC","AD","AE","AF","AG","AH","AI","AJ","AK","AL","AM",
                        "AN","AO","AP"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CsvModules>() {{
                setTargetType(CsvModules.class);
            }});
        }});
        return csvItemReader;
    }


    public List<CsvModules> lanuchAll(){
        try {
            FlatFileItemReader<CsvModules> flatFileItemReader = csvItemReader4All();
            flatFileItemReader.open(new ExecutionContext());
            int index = 0;
            for (;;){
                CsvModules csvModules = flatFileItemReader.read();
                if(null!=csvModules){
                    allList.add(csvModules);
                    index++;
                    System.out.println(String.format("allList size : "+index));
                }else{
                    break;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CsvModules> lanuchServen(){
        try {
            FlatFileItemReader<CsvModules> flatFileItemReader = csvItemReader4All();
            flatFileItemReader.open(new ExecutionContext());
            CsvModules csvModules = flatFileItemReader.read();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[]args){

    }

}