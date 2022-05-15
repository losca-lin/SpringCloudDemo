package com.tjnu.losca.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Losca
 * @date 2022/2/5 11:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage {
    private String to;
    private String text;
    private String subject;
}
