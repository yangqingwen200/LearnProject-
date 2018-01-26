package com.filter;

import com.alibaba.rocketmq.common.filter.MessageFilter;

import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * this class not permission include chinese word
 */
public class MessageFilterImpl implements MessageFilter {

    public boolean match(MessageExt msg) {

        String property = msg.getUserProperty("id");

        if (property != null) {

            int id = Integer.parseInt(property);

            if (id % 3 == 0) {

                return true;

            }

        }

        return false;

    }

}