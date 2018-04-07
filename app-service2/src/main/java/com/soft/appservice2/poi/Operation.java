package com.soft.appservice2.poi;


import com.google.common.collect.Lists;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.support.SybasePagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.validation.BindException;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * excel,csv 操作
 */
public class Operation {

    public static List<CsvModules> allList = Lists.newArrayList();
    public static List<CsvServenModules> servenList = Lists.newArrayList();

    public static FlatFileItemReader<CsvModules> csvItemReader4All(String classPath) {
        FlatFileItemReader<CsvModules> csvItemReader = new FlatFileItemReader<>();
        csvItemReader.setResource(new ClassPathResource(classPath));
        csvItemReader.setEncoding("GBK");
        csvItemReader.setLinesToSkip(1);
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

    public static FlatFileItemReader<CsvServenModules> csvItemReader4Serven(String classPath) {
        FlatFileItemReader<CsvServenModules> csvItemReader = new FlatFileItemReader<>();
        csvItemReader.setResource(new ClassPathResource(classPath));
        csvItemReader.setEncoding("GBK");
        csvItemReader.setLinesToSkip(1);
        csvItemReader.setLineMapper(new DefaultLineMapper<CsvServenModules>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"A","B","C","D","E","F","G","H","I","J","K","L"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CsvServenModules>() {{
                setTargetType(CsvServenModules.class);
            }});
        }});
        return csvItemReader;
    }





    public static List<CsvServenModules> lanuchServen(){
        try {
            FlatFileItemReader<CsvServenModules> flatFileItemReader = csvItemReader4Serven("data/serven.csv");
            flatFileItemReader.open(new ExecutionContext());
            int index = 0;
            for (;;){
                CsvServenModules csvServenModules = flatFileItemReader.read();
                if(null!=csvServenModules){
                    servenList.add(csvServenModules);
                    index++;
                    System.out.println(csvServenModules.getA()+"-"+csvServenModules.getB()+"-"+csvServenModules.getC()+csvServenModules.getD()+"-"+csvServenModules.getE()+"-"+csvServenModules.getF());
                    //System.out.println(String.format("allList size : "+index));
                }else{
                    break;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<CsvModules> lanuchAll(){
        try {
            FlatFileItemReader<CsvModules> flatFileItemReader = csvItemReader4All("data/all.csv");
            flatFileItemReader.open(new ExecutionContext());
            int index = 0;
            for (;;){
                CsvModules csvModules = flatFileItemReader.read();
                if(null!=csvModules){
                    allList.add(csvModules);
                    index++;
                    System.out.println(csvModules.getA()+"-"+csvModules.getB()+"-"+csvModules.getC()+csvModules.getD()+"-"+csvModules.getE()+"-"+csvModules.getF());
                    //System.out.println(String.format("allList size : "+index));
                }else{
                    break;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return allList;
    }



    public static void main(String[]args){


        try {
            List<CsvModules> allList = lanuchAll();
            Set<String> set = ExcelToDB.exportListFromExcel();
            String servenStr = (String) set.stream().collect(Collectors.joining(","));
            List<CsvModules> matchList = allList.stream().filter(x->set.parallelStream().anyMatch(s->s.equals(x.getI()))).collect(Collectors.toList());
             System.out.println();
            matchList.stream().forEach(x-> System.out.println(x.getA()+"#"+x.getI()+"#"));
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}