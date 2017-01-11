@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(value = DateTimeAdapter.class, type = DateTime.class)
})

package hu.farago.repo.model.entity.mongo.embedded.edgar;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

import org.joda.time.DateTime;