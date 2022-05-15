package com.tjnu.losca.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Losca
 * @date 2022/1/13 20:32
 */
@Data
public class BasePojo {
    @TableId(type = IdType.AUTO)
    private Long id;
}
