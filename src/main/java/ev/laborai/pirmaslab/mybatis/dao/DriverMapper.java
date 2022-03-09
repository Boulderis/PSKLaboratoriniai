package ev.laborai.pirmaslab.mybatis.dao;

import ev.laborai.pirmaslab.mybatis.model.Driver;
import java.util.List;

public interface DriverMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.DRIVER
     *
     * @mbg.generated Wed Mar 09 16:30:46 EET 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.DRIVER
     *
     * @mbg.generated Wed Mar 09 16:30:46 EET 2022
     */
    int insert(Driver record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.DRIVER
     *
     * @mbg.generated Wed Mar 09 16:30:46 EET 2022
     */
    Driver selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.DRIVER
     *
     * @mbg.generated Wed Mar 09 16:30:46 EET 2022
     */
    List<Driver> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.DRIVER
     *
     * @mbg.generated Wed Mar 09 16:30:46 EET 2022
     */
    int updateByPrimaryKey(Driver record);
}