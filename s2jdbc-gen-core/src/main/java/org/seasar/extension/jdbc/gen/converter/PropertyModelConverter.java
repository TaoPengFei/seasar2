/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.extension.jdbc.gen.converter;

import org.seasar.extension.jdbc.gen.GenDialect;
import org.seasar.extension.jdbc.gen.model.ColumnModel;
import org.seasar.extension.jdbc.gen.model.PropertyModel;
import org.seasar.framework.convention.PersistenceConvention;

/**
 * @author taedium
 * 
 */
public class PropertyModelConverter {

    private PersistenceConvention persistenceConvention;

    private GenDialect dialect;

    private String versionColumn;

    public void setPersistenceConvention(
            PersistenceConvention persistenceConvention) {
        this.persistenceConvention = persistenceConvention;
    }

    public void setGenerationDialect(GenDialect generationDialect) {
        this.dialect = generationDialect;
    }

    public void setVersionColumn(String versionColumn) {
        this.versionColumn = versionColumn;
    }

    public PropertyModel convert(ColumnModel columnModel) {
        PropertyModel propertyModel = new PropertyModel();
        doName(columnModel, propertyModel);
        doId(columnModel, propertyModel);
        doLob(columnModel, propertyModel);
        doPropertyClass(columnModel, propertyModel);
        doTemporalType(columnModel, propertyModel);
        doTransient(columnModel, propertyModel);
        doVersion(columnModel, propertyModel);
        return propertyModel;
    }

    protected void doName(ColumnModel columnModel, PropertyModel propertyModel) {
        propertyModel.setName(persistenceConvention
                .fromColumnNameToPropertyName(columnModel.getName()));
    }

    protected void doId(ColumnModel columnModel, PropertyModel propertyModel) {
        propertyModel.setId(columnModel.isPrimaryKey());
    }

    protected void doPropertyClass(ColumnModel columnModel,
            PropertyModel propertyModel) {
        Class<?> clazz = dialect.getJavaType(
                columnModel.getSqlType(), columnModel.getTypeName(),
                columnModel.isNullable());
        propertyModel.setPropertyClass(clazz);
    }

    protected void doLob(ColumnModel columnModel, PropertyModel propertyModel) {
        propertyModel.setLob(dialect.isLobType(columnModel
                .getSqlType(), columnModel.getTypeName()));
    }

    protected void doTemporalType(ColumnModel columnModel,
            PropertyModel propertyModel) {
        propertyModel.setTemporalType(dialect.getTemporalType(
                columnModel.getSqlType(), columnModel.getTypeName()));
    }

    protected void doTransient(ColumnModel columnModel,
            PropertyModel propertyModel) {
    }

    protected void doVersion(ColumnModel columnModel,
            PropertyModel propertyModel) {
        if (versionColumn.equalsIgnoreCase(columnModel.getName())) {
            propertyModel.setVersion(true);
        }
    }
}
