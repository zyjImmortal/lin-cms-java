package com.lin.cms.mbg.mapper;

import com.lin.cms.mbg.model.CmsAuth;
import com.lin.cms.mbg.model.CmsAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsAuthMapper {
    int countByExample(CmsAuthExample example);

    int deleteByExample(CmsAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsAuth record);

    int insertSelective(CmsAuth record);

    List<CmsAuth> selectByExample(CmsAuthExample example);

    CmsAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsAuth record, @Param("example") CmsAuthExample example);

    int updateByExample(@Param("record") CmsAuth record, @Param("example") CmsAuthExample example);

    int updateByPrimaryKeySelective(CmsAuth record);

    int updateByPrimaryKey(CmsAuth record);
}