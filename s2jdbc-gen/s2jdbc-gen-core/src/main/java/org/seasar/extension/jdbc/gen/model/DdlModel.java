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
package org.seasar.extension.jdbc.gen.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.seasar.extension.jdbc.gen.desc.SequenceDesc;
import org.seasar.extension.jdbc.gen.desc.TableDesc;
import org.seasar.extension.jdbc.gen.dialect.GenDialect;

/**
 * DDLのモデルです。
 * 
 * @author taedium
 */
public class DdlModel {

    /** 方言 */
    protected GenDialect dialect;

    /** SQLのキーワードの大文字小文字を変換するかどうかを示す列挙型 */
    protected SqlKeywordCaseType sqlKeywordCaseType;

    /** SQLの識別子の大文字小文字を変換するかどうかを示す列挙型 */
    protected SqlIdentifierCaseType sqlIdentifierCaseType;

    /** 区切り文字 */
    protected char delimiter;

    /** テーブルオプション */
    protected String tableOption;

    /** スキーマ情報を格納するテーブル名 */
    protected String schemaInfoFullTableName;

    /** スキーマのバージョン番号を格納するカラム名 */
    protected String schemaInfoColumnName;

    /** スキーマのバージョン番号を格納するカラムの定義 */
    protected String schemaInfoColumnDefinition;

    /** スキーマのバージョン番号 */
    protected int versionNo;

    /** テーブル記述のリスト */
    protected List<TableDesc> tableDescList = new ArrayList<TableDesc>();

    /** シーケンス記述のリスト */
    protected List<SequenceDesc> sequenceDescList = new ArrayList<SequenceDesc>();

    /**
     * 方言を返します。
     * 
     * @return 方言
     */
    public GenDialect getDialect() {
        return dialect;
    }

    /**
     * 方言を設定します。
     * 
     * @param dialect
     *            方言
     */
    public void setDialect(GenDialect dialect) {
        this.dialect = dialect;
    }

    /**
     * @return Returns the sqlKeywordCaseType.
     */
    public SqlKeywordCaseType getSqlKeywordCaseType() {
        return sqlKeywordCaseType;
    }

    /**
     * @param sqlKeywordCaseType
     *            The sqlKeywordCaseType to set.
     */
    public void setSqlKeywordCaseType(SqlKeywordCaseType sqlKeywordCaseType) {
        this.sqlKeywordCaseType = sqlKeywordCaseType;
    }

    /**
     * @return Returns the sqlIdentifierCaseType.
     */
    public SqlIdentifierCaseType getSqlIdentifierCaseType() {
        return sqlIdentifierCaseType;
    }

    /**
     * @param sqlIdentifierCaseType
     *            The sqlIdentifierCaseType to set.
     */
    public void setSqlIdentifierCaseType(
            SqlIdentifierCaseType sqlIdentifierCaseType) {
        this.sqlIdentifierCaseType = sqlIdentifierCaseType;
    }

    /**
     * 区切り文字を返します。
     * 
     * @return 区切り文字
     */
    public char getDelimiter() {
        return delimiter;
    }

    /**
     * 区切り文字を設定します。
     * 
     * @param delimiter
     *            区切り文字
     */
    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * テーブルオプションを返します。
     * 
     * @return 存在する場合テーブルオプション、存在しない場合{@code null}
     */
    public String getTableOption() {
        return convertKeywordInternal(tableOption);
    }

    /**
     * テーブルオプションを設定します。
     * 
     * @param tableOption
     *            テーブルオプション
     */
    public void setTableOption(String tableOption) {
        this.tableOption = tableOption;
    }

    /**
     * スキーマ情報を格納するテーブル名を返します。
     * 
     * @return スキーマ情報を格納するテーブル名
     */
    public String getSchemaInfoFullTableName() {
        return schemaInfoFullTableName;
    }

    /**
     * スキーマ情報を格納するテーブル名を設定します。
     * 
     * @param schemaInfoFullTableName
     *            スキーマ情報を格納するテーブル名
     */
    public void setSchemaInfoFullTableName(String schemaInfoFullTableName) {
        this.schemaInfoFullTableName = schemaInfoFullTableName;
    }

    /**
     * スキーマのバージョン番号を格納するカラム名を返します。
     * 
     * @return スキーマのバージョン番号を格納するカラム名
     */
    public String getSchemaInfoColumnName() {
        return schemaInfoColumnName;
    }

    /**
     * スキーマのバージョン番号を格納するカラム名を設定します。
     * 
     * @param schemaInfoColumnName
     *            スキーマのバージョン番号を格納するカラム名
     */
    public void setSchemaInfoColumnName(String schemaInfoColumnName) {
        this.schemaInfoColumnName = schemaInfoColumnName;
    }

    /**
     * バージョン番号を返します。
     * 
     * @return バージョン番号
     */
    public int getVersionNo() {
        return versionNo;
    }

    /**
     * バージョン番号を設定します。
     * 
     * @param versionNo
     *            バージョン番号
     */
    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * スキーマのバージョン番号を格納するカラムの定義を返します。
     * 
     * @return スキーマのバージョン番号を格納するカラムの定義
     */
    public String getSchemaInfoColumnDefinition() {
        return schemaInfoColumnDefinition;
    }

    /**
     * スキーマのバージョン番号を格納するカラムの定義を設定します。
     * 
     * @param schemaInfoColumnDefinition
     *            スキーマのバージョン番号を格納するカラムの定義
     */
    public void setSchemaInfoColumnDefinition(String schemaInfoColumnDefinition) {
        this.schemaInfoColumnDefinition = schemaInfoColumnDefinition;
    }

    /**
     * テーブル記述を追加します。
     * 
     * @param tableDesc
     *            テーブル記述
     */
    public void addTableDesc(TableDesc tableDesc) {
        if (tableDescList.contains(tableDesc)) {
            return;
        }
        tableDescList.add(tableDesc);
    }

    /**
     * テーブル記述のリストを返します。
     * 
     * @return テーブル記述のリスト
     */
    public List<TableDesc> getTableDescList() {
        return Collections.unmodifiableList(tableDescList);
    }

    /**
     * シーケンス記述を追加します。
     * 
     * @param sequenceDesc
     *            シーケンス記述
     */
    public void addSequenceDesc(SequenceDesc sequenceDesc) {
        if (sequenceDescList.contains(sequenceDesc)) {
            return;
        }
        sequenceDescList.add(sequenceDesc);
    }

    /**
     * シーケンス記述のリストを返します。
     * 
     * @return シーケンス記述のリスト
     */
    public List<SequenceDesc> getSequenceDescList() {
        return Collections.unmodifiableList(sequenceDescList);
    }

    /**
     * シーケンス定義の断片を返します。
     * 
     * @param sequenceDesc
     *            シーケンス記述
     * @return シーケンス定義の断片
     */
    public String getSequenceDefinitionFragment(SequenceDesc sequenceDesc) {
        String value = dialect.getSequenceDefinitionFragment(sequenceDesc
                .getDataType(), sequenceDesc.getInitialValue(), sequenceDesc
                .getAllocationSize());
        return convertKeywordInternal(value);
    }

    /**
     * IDENTITYカラムの定義を返します。
     * 
     * @return IDENTITYカラムの定義
     */
    public String getIdentityColumnDefinition() {
        String value = dialect.getIdentityColumnDefinition();
        return convertKeywordInternal(value);
    }

    /**
     * 外部キーを削除する構文を返します。
     * 
     * @return 外部キーを削除する構文
     */
    public String getDropForeignKeySyntax() {
        String value = dialect.getDropForeignKeySyntax();
        return convertKeywordInternal(value);
    }

    /**
     * 一意キーを削除する構文を返します。
     * 
     * @return 外部キーを削除する構文
     */
    public String getDropUniqueKeySyntax() {
        String value = dialect.getDropUniqueKeySyntax();
        return convertKeywordInternal(value);
    }

    /**
     * クォートで囲みます。
     * 
     * @param value
     *            値
     * @return クォートで囲まれた値
     */
    public String quote(String value) {
        return dialect.quote(value);
    }

    /**
     * クォートを取り除きます。
     * 
     * @param value
     *            値
     * @return クォートが取り除かれた値
     */
    public String unquote(String value) {
        return dialect.unquote(value);
    }

    /**
     * SQLのキーワードの大文字小文字を変換します。
     * 
     * @param keyword
     *            SQLのキーワード
     * @return 変換された文字列
     */
    public String convertKeyword(String keyword) {
        return convertKeywordInternal(keyword);
    }

    /**
     * 内部的にSQLのキーワードの大文字小文字を変換します。
     * 
     * @param keyword
     *            SQLのキーワード
     * @return 変換された文字列
     */
    protected String convertKeywordInternal(String keyword) {
        if (keyword == null) {
            return null;
        }
        switch (sqlKeywordCaseType) {
        case UPPERCASE:
            return keyword.toUpperCase();
        case LOWERCASE:
            return keyword.toLowerCase();
        default:
            return keyword;
        }
    }

    /**
     * SQLのキーワードの識別子を変換します。
     * 
     * @param identifier
     *            SQLの識別子
     * @return 変換された文字列
     */
    public String convertIdentifier(String identifier) {
        return convertIdentifierInternal(identifier);
    }

    /**
     * 内部的にSQLの識別子の大文字小文字を変換します。
     * 
     * @param identifier
     *            SQLの識別子
     * @return 変換された文字列
     */
    protected String convertIdentifierInternal(String identifier) {
        if (identifier == null) {
            return null;
        }
        switch (sqlIdentifierCaseType) {
        case UPPERCASE:
            return identifier.toUpperCase();
        case LOWERCASE:
            return identifier.toLowerCase();
        default:
            return identifier;
        }
    }
}
