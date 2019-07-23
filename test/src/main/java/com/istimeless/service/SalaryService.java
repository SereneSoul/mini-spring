package com.istimeless.service;

import com.istimeless.beans.Bean;

/**
 * @author lijiayin
 */
@Bean
public class SalaryService {
    public Integer calSalary(Integer experience){
        return experience * 5000;
    }
}
