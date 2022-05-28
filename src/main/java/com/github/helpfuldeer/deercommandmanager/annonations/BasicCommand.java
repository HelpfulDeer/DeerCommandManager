package com.github.helpfuldeer.deercommandmanager.annonations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BasicCommand {
    String name();

    String description();

    String usage();

    String permission() default "";

    String[] aliases() default {""};
}