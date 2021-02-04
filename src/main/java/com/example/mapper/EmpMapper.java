package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Emp;

public interface EmpMapper extends BaseMapper<Emp> {

    public Emp selectByCondition();

}
