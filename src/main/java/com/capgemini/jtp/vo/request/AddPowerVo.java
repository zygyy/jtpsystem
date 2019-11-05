package com.capgemini.jtp.vo.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AddPowerVo {
    private int roleId;
    List<Integer> nodeIds;

}
