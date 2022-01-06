package com.workshop12.numbers.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.workshop12.numbers.model.Generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GenerateController {
    private Logger logger = LoggerFactory.getLogger(GenerateController.class);
    
    @GetMapping ("/generate")
    public String showGenerateForm(Model model){
        Generate generate = new Generate();
        // generate.setNumberVal(8);
        model.addAttribute("generate", generate);
        return "generate"; // return to html temp file
    }

    @PostMapping ("/generate")
    public String generateNumbers(@ModelAttribute Generate generate, Model model){
        logger.info("From the form "+generate.getNumberVal());
        int numberRandomNumbers = generate.getNumberVal();
        if (numberRandomNumbers > 10){
            //throw new RandomNumberException();
            model.addAttribute("errorMessage", "Exceed 10 already");
            return "error";
        }
        String[] imgNumbers = {"1.jpeg","2.jpeg","3.jpeg","4.jpeg","5.jpeg","6.jpeg",
                                "7.jpeg","8.jpeg","9.jpeg","10.jpeg"};
        List<String> selectedImg = new ArrayList<String>();
        Random randNum = new Random();
        Set<Integer> uniqueGeneratRanNumSet = new LinkedHashSet<Integer>();
        while(uniqueGeneratRanNumSet.size()<numberRandomNumbers) {
            Integer resultOfRandNumbers = randNum.nextInt(generate.getNumberVal()+1);
            uniqueGeneratRanNumSet.add(resultOfRandNumbers);
        }
        Iterator<Integer> it = uniqueGeneratRanNumSet.iterator();
        Integer currentElem = null;
        while(it.hasNext()){
            currentElem = it.next();
            logger.info("currentElem > "+currentElem);
            selectedImg.add(imgNumbers[currentElem.intValue()]);

        }
        model.addAttribute("randNumsResult", selectedImg.toArray());
        return "result";
    }
    
}
