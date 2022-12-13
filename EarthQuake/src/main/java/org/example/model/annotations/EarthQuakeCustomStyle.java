package org.example.model.annotations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.immutables.value.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.PACKAGE, ElementType.TYPE})
@JsonIgnoreProperties(ignoreUnknown = true)
@Value.Style(jdkOnly = true, visibility = Value.Style.ImplementationVisibility.PRIVATE)
public @interface EarthQuakeCustomStyle {
}
