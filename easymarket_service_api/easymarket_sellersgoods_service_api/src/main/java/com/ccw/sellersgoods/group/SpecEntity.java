package com.ccw.sellersgoods.group;

import com.ccw.sellersgoods.pojo.Specification;
import com.ccw.sellersgoods.pojo.SpecificationOption;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class SpecEntity implements Serializable {
    @ApiModelProperty(value = "规格对象",required = false)
    private Specification specification;
    @ApiModelProperty(value = "规格选项对象",required = false)
    private List<SpecificationOption> specificationOptionList;

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public List<SpecificationOption> getSpecificationOptionList() {
        return specificationOptionList;
    }

    public void setSpecificationOptionList(List<SpecificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }
}
