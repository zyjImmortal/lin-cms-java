package com.lin.cms.mbg.mapper;

import com.lin.cms.mbg.model.CmsGroup;
import com.lin.cms.mbg.model.CmsGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsGroupMapper {
    int countByExample(CmsGroupExample example);

    int deleteByExample(CmsGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsGroup record);

    int insertSelective(CmsGroup record);

    List<CmsGroup> selectByExample(CmsGroupExample example);

    CmsGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsGroup record, @Param("example") CmsGroupExample example);

    int updateByExample(@Param("record") CmsGroup record, @Param("example") CmsGroupExample example);

    int updateByPrimaryKeySelective(CmsGroup record);

    int updateByPrimaryKey(CmsGroup record);
}