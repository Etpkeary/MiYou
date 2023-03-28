package com.miyou.miyou_backend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求
 *
 * @author LINGLAN
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}