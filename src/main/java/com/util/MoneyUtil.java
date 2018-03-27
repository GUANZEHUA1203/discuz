/*
 * Copyright (C), 2002-2017, 重庆锋云汇智数据科技有限公司
 * FileName: MoneyUtil.java
 * Author:   qxf
 * Date:     2017年5月5日 上午11:17:53
 */
package com.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额的转换工具类
 *
 * @author pengkun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public final class MoneyUtil
{
    private static final BigDecimal MONEY_PER = new BigDecimal(100);
    
    private static final BigDecimal RATIO_PER = new BigDecimal(10000);
    
    private static final int SCALE_SIZE_MONEY = 2;
    
    private static final int SCALE_SIZE_RATIO = 4;
    
    /**
     * 构造方法
     */
    private MoneyUtil()
    {
    }

    /**
     * 分转元，保留两位小数，可以根据SCALE_SIZE_MONEY进行配置
     * @param money
     * @return
     */
    public static BigDecimal moneyToDecimal(long money)
    {
        BigDecimal orc = new BigDecimal(money);
        return orc.divide(MONEY_PER).setScale(SCALE_SIZE_MONEY, RoundingMode.HALF_EVEN);
    }

    /**
     * 元转分
     * @param money
     * @return
     */
    public static long moneyToLong(BigDecimal money)
    {
        if (money.scale() > SCALE_SIZE_MONEY)
        {
            //throw new IllegalArgumentException("scale more than system needs.");
        }
        
        BigDecimal temp = money.setScale(SCALE_SIZE_MONEY, RoundingMode.HALF_EVEN);
        return temp.multiply(MONEY_PER).longValue();
    }

    /**
     * 比例转换
     * @param ratio
     * @return
     */
    public static BigDecimal ratioToDecimal(long ratio)
    {
        BigDecimal orc = new BigDecimal(ratio);
        return orc.divide(RATIO_PER).setScale(SCALE_SIZE_RATIO, RoundingMode.HALF_EVEN);
    }
    
    public static long ratioToLong(BigDecimal money)
    {
        if (money.scale() > SCALE_SIZE_RATIO)
        {
            //throw new IllegalArgumentException("scale more than system needs.");
        }
        
        BigDecimal temp = money.setScale(SCALE_SIZE_RATIO, RoundingMode.HALF_EVEN);
        return temp.multiply(RATIO_PER).longValue();
    }
}
