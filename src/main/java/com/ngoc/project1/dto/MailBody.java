package com.ngoc.project1.dto;

import lombok.Builder;

@Builder
public record MailBody (String to, String subject, String text){
}
