import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Emp;
import com.example.mapper.EmpMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("spring.xml");

    @Test
    public void test01() throws SQLException {
        DruidDataSource druidDataSource = context.getBean("dataSource", DruidDataSource.class);
        System.out.println(druidDataSource.getConnection());
    }

    @Test
    public void select(){
        EmpMapper empMapper = context.getBean("empMapper", EmpMapper.class);
        List<Emp> emps = empMapper.selectList(null);
        for (Emp emp : emps) {
            System.out.println(emp);
        }
    }

    @Test
    public void update(){
        EmpMapper empMapper = context.getBean("empMapper", EmpMapper.class);
        Emp emp = new Emp();
        emp.setEmpno(7369);
        emp.setEname("SMITH");
        int i = empMapper.updateById(emp);
    }

    @Test
    public void delete(){
        EmpMapper empMapper = context.getBean("empMapper", EmpMapper.class);
        //批量删除
//        empMapper.deleteBatchIds(Arrays.asList(2,3));
//        根据主键删除
//        empMapper.deleteById(1);
//        根据map删除,k为列名
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("empno",1);
//        empMapper.deleteByMap(map);
        //默认的删除方式需要传入一个QueryWapper
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("empno",1);
        empMapper.delete(queryWrapper);
    }

    @Test
    public void sel(){
        EmpMapper empMapper = context.getBean("empMapper", EmpMapper.class);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ename","SMITH");
        queryWrapper.eq("empno",7900);
        //返回的数据有且仅有一条，如果出现多条会报错
//        System.out.println(empMapper.selectOne(queryWrapper));
        //根据主键查询 返回一条结果
//        System.out.println(empMapper.selectById(7369));
        //根据多个主键id进行查询，返回多条数据
//        List<Emp> emps = empMapper.selectBatchIds(Arrays.asList(7369, 7499, 7698));
//        for (Emp emp : emps) {
//            System.out.println(emp);
//        }
        //分页查询
//        Page<Emp> empPage = empMapper.selectPage(new Page<Emp>(2, 2), null);
//        System.out.println(empPage.getRecords());
        //自定义查询
        empMapper.selectByCondition();
    }

}
