-- liquibase formatted sql

-- changeset pc-hp1:1635881616693-152
CREATE TABLE accounts_currency_view (acc_no INTEGER NOT NULL, cur_code VARCHAR(255) NOT NULL, active BOOLEAN, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), currency_d_name VARCHAR(255), currency_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), used BOOLEAN, CONSTRAINT "accounts_currency_viewPK" PRIMARY KEY (acc_no, cur_code));

-- changeset pc-hp1:1635881616693-153
CREATE TABLE accounts_group_view (group_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), group_d_name VARCHAR(255), group_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), CONSTRAINT "accounts_group_viewPK" PRIMARY KEY (group_no));

-- changeset pc-hp1:1635881616693-154
CREATE TABLE branches_view (branch_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), branch_d_address VARCHAR(255), branch_d_name VARCHAR(255), branch_f_address VARCHAR(255), branch_f_name VARCHAR(255), capital INTEGER, city_d_name VARCHAR(255), city_f_name VARCHAR(255), city_no INTEGER, company_d_name VARCHAR(255), company_f_name VARCHAR(255), company_no INTEGER, country_d_name VARCHAR(255), country_f_name VARCHAR(255), country_no INTEGER, cr_no INTEGER, logo VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), province_d_name VARCHAR(255), province_f_name VARCHAR(255), province_no INTEGER, report_d_header1 VARCHAR(255), report_d_header2 VARCHAR(255), report_d_header3 VARCHAR(255), report_f_header1 VARCHAR(255), report_f_header2 VARCHAR(255), report_f_header3 VARCHAR(255), shortcut_d VARCHAR(255), shortcut_f VARCHAR(255), tax_no INTEGER, telephone_no VARCHAR(255), CONSTRAINT "branches_viewPK" PRIMARY KEY (branch_no));

-- changeset pc-hp1:1635881616693-155
CREATE TABLE chart_of_accounts_view (acc_no INTEGER NOT NULL, acc_d_name VARCHAR(255), acc_dtl VARCHAR(255), acc_f_name VARCHAR(255), acc_group INTEGER, acc_type VARCHAR(255), add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), bs BOOLEAN, cash_flow_type VARCHAR(255), cc_post VARCHAR(255), dr BOOLEAN, group_d_name VARCHAR(255), group_f_name VARCHAR(255), inactive BOOLEAN, inactive_date TIMESTAMP WITHOUT TIME ZONE, inactive_reason VARCHAR(255), inactive_user INTEGER, inactive_user_d_name VARCHAR(255), inactive_user_f_name VARCHAR(255), level INTEGER, modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), parent_acc INTEGER, parent_acc_d_name VARCHAR(255), parent_acc_f_name VARCHAR(255), sub BOOLEAN, CONSTRAINT "chart_of_accounts_viewPK" PRIMARY KEY (acc_no));

-- changeset pc-hp1:1635881616693-156
CREATE TABLE city_view (city_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), city_d_name VARCHAR(255), city_f_name VARCHAR(255), country_no INTEGER, country_no_d_name VARCHAR(255), country_no_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), province_no INTEGER, province_no_d_name VARCHAR(255), province_no_f_name VARCHAR(255), region_no INTEGER, region_no_d_name VARCHAR(255), region_no_f_name VARCHAR(255), shortcut VARCHAR(255), CONSTRAINT "city_viewPK" PRIMARY KEY (city_no));

-- changeset pc-hp1:1635881616693-157
CREATE TABLE company_groups_view (group_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), group_d_name VARCHAR(255), group_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), CONSTRAINT "company_groups_viewPK" PRIMARY KEY (group_no));

-- changeset pc-hp1:1635881616693-158
CREATE TABLE company_view (company_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), company_d_name VARCHAR(255), company_f_name VARCHAR(255), company_group INTEGER, company_mail VARCHAR(255), company_website VARCHAR(255), country_d_name VARCHAR(255), country_f_name VARCHAR(255), country_no INTEGER, group_d_name VARCHAR(255), group_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), shortcut_d VARCHAR(255), shortcut_f VARCHAR(255), CONSTRAINT "company_viewPK" PRIMARY KEY (company_no));

-- changeset pc-hp1:1635881616693-159
CREATE TABLE cost_center_group_view (group_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), group_d_name VARCHAR(255), group_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), CONSTRAINT "cost_center_group_viewPK" PRIMARY KEY (group_no));

-- changeset pc-hp1:1635881616693-160
CREATE TABLE cost_center_view (cc_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), cc_d_name VARCHAR(255), cc_f_name VARCHAR(255), cc_group INTEGER, group_d_name VARCHAR(255), group_f_name VARCHAR(255), inactive BOOLEAN, inactive_date TIMESTAMP WITHOUT TIME ZONE, inactive_reason VARCHAR(255), inactive_user INTEGER, inactive_user_d_name VARCHAR(255), inactive_user_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), parent_cc INTEGER, parent_cc_d_name VARCHAR(255), parent_cc_f_name VARCHAR(255), sub BOOLEAN, CONSTRAINT "cost_center_viewPK" PRIMARY KEY (cc_no));

-- changeset pc-hp1:1635881616693-161
CREATE TABLE country_view (country_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), country_d_name VARCHAR(255), country_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), region_no INTEGER, region_no_d_name VARCHAR(255), region_no_f_name VARCHAR(255), shortcut VARCHAR(255), CONSTRAINT "country_viewPK" PRIMARY KEY (country_no));

-- changeset pc-hp1:1635881616693-162
CREATE TABLE currency_history_view (currency_code VARCHAR(255) NOT NULL, modify_date TIMESTAMP WITHOUT TIME ZONE NOT NULL, exchange_rate numeric(19, 2), max_ex_rate numeric(19, 2), min_ex_rate numeric(19, 2), modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), CONSTRAINT "currency_history_viewPK" PRIMARY KEY (currency_code, modify_date));

-- changeset pc-hp1:1635881616693-163
CREATE TABLE currency_values_view (currency_code VARCHAR(255) NOT NULL, value INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), CONSTRAINT "currency_values_viewPK" PRIMARY KEY (currency_code, value));

-- changeset pc-hp1:1635881616693-164
CREATE TABLE currency_view (currency_code VARCHAR(255) NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), currency_d_name VARCHAR(255), currency_f_name VARCHAR(255), exchange_rate numeric(19, 2), fraction_d_name VARCHAR(255), fraction_f_name VARCHAR(255), fraction_no INTEGER, local_currency BOOLEAN, max_ex_rate numeric(19, 2), min_ex_rate numeric(19, 2), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), pos_ex_rate numeric(19, 2), CONSTRAINT "currency_viewPK" PRIMARY KEY (currency_code));

-- changeset pc-hp1:1635881616693-165
CREATE TABLE emp_info_view (emp_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), emp_d_name VARCHAR(255), emp_f_name VARCHAR(255), inactive BOOLEAN, inactive_date TIMESTAMP WITHOUT TIME ZONE, inactive_reason VARCHAR(255), inactive_user INTEGER, inactive_user_d_name VARCHAR(255), inactive_user_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), CONSTRAINT "emp_info_viewPK" PRIMARY KEY (emp_no));

-- changeset pc-hp1:1635881616693-166
CREATE TABLE flag_detail_main_tree (flag_code VARCHAR(255) NOT NULL, flag_value INTEGER NOT NULL, user_id INTEGER NOT NULL, active BOOLEAN, add_date TIMESTAMP WITHOUT TIME ZONE, add_priv BOOLEAN, add_user INTEGER, delete_priv BOOLEAN, flag_priv BOOLEAN, flag_sr INTEGER, label_code VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_priv BOOLEAN, modify_user INTEGER, print_priv BOOLEAN, view_priv BOOLEAN, CONSTRAINT "flag_detail_main_treePK" PRIMARY KEY (flag_code, flag_value, user_id));

-- changeset pc-hp1:1635881616693-167
CREATE TABLE flag_detail_view (flag_code VARCHAR(255) NOT NULL, flag_value INTEGER NOT NULL, active BOOLEAN, flag_priv BOOLEAN, flag_sr INTEGER, label_code VARCHAR(255), CONSTRAINT "flag_detail_viewPK" PRIMARY KEY (flag_code, flag_value));

-- changeset pc-hp1:1635881616693-168
CREATE TABLE flag_master_view (flag_code VARCHAR(255) NOT NULL, label_code VARCHAR(255), CONSTRAINT "flag_master_viewPK" PRIMARY KEY (flag_code));

-- changeset pc-hp1:1635881616693-169
CREATE TABLE flag_priv_view (flag_code VARCHAR(255) NOT NULL, flag_value INTEGER NOT NULL, user_id INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_priv BOOLEAN, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), delete_priv BOOLEAN, modify_date TIMESTAMP WITHOUT TIME ZONE, modify_priv BOOLEAN, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), print_priv BOOLEAN, user_d_name VARCHAR(255), user_f_name VARCHAR(255), view_priv BOOLEAN, CONSTRAINT "flag_priv_viewPK" PRIMARY KEY (flag_code, flag_value, user_id));

-- changeset pc-hp1:1635881616693-170
CREATE TABLE form_privilage_view (form_no INTEGER NOT NULL, user_id INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_priv BOOLEAN, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), audit_priv BOOLEAN, delete_priv BOOLEAN, form_d_name VARCHAR(255), form_f_name VARCHAR(255), include_priv BOOLEAN, modify_date TIMESTAMP WITHOUT TIME ZONE, modify_priv BOOLEAN, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), post_priv BOOLEAN, print_priv BOOLEAN, view_priv BOOLEAN, CONSTRAINT "form_privilage_viewPK" PRIMARY KEY (form_no, user_id));

-- changeset pc-hp1:1635881616693-171
CREATE TABLE forms_view (form_no INTEGER NOT NULL, active BOOLEAN, flag_code VARCHAR(255), form_d_name VARCHAR(255), form_f_name VARCHAR(255), form_order INTEGER, main BOOLEAN, module_no INTEGER, module_no_d_name VARCHAR(255), module_no_f_name VARCHAR(255), parent_form INTEGER, parent_form_d_name VARCHAR(255), parent_form_f_name VARCHAR(255), CONSTRAINT "forms_viewPK" PRIMARY KEY (form_no));

-- changeset pc-hp1:1635881616693-172
CREATE TABLE labels_view (label_code VARCHAR(255) NOT NULL, lang_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), label_desc VARCHAR(255), lang_no_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), CONSTRAINT "labels_viewPK" PRIMARY KEY (label_code, lang_no));

-- changeset pc-hp1:1635881616693-173
CREATE TABLE language_view (lang_no INTEGER NOT NULL, active BOOLEAN, lang_dfl BOOLEAN, lang_dir INTEGER, lang_ext VARCHAR(255), lang_name VARCHAR(255), report_ext VARCHAR(255), CONSTRAINT "language_viewPK" PRIMARY KEY (lang_no));

-- changeset pc-hp1:1635881616693-174
CREATE TABLE messages_view (lang_no INTEGER NOT NULL, message_code VARCHAR(255) NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), lang_no_name VARCHAR(255), message_desc VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), CONSTRAINT "messages_viewPK" PRIMARY KEY (lang_no, message_code));

-- changeset pc-hp1:1635881616693-175
CREATE TABLE modules_view (module_no INTEGER NOT NULL, active BOOLEAN, module_d_name VARCHAR(255), module_f_name VARCHAR(255), order_no INTEGER, shortcut VARCHAR(255), CONSTRAINT "modules_viewPK" PRIMARY KEY (module_no));

-- changeset pc-hp1:1635881616693-176
CREATE TABLE province_view (province_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), country_no INTEGER, country_no_d_name VARCHAR(255), country_no_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), province_d_name VARCHAR(255), province_f_name VARCHAR(255), region_no INTEGER, region_no_d_name VARCHAR(255), region_no_f_name VARCHAR(255), shortcut VARCHAR(255), CONSTRAINT "province_viewPK" PRIMARY KEY (province_no));

-- changeset pc-hp1:1635881616693-177
CREATE TABLE region_view (region_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), region_d_name VARCHAR(255), region_f_name VARCHAR(255), shortcut VARCHAR(255), CONSTRAINT "region_viewPK" PRIMARY KEY (region_no));

-- changeset pc-hp1:1635881616693-178
CREATE TABLE users_groups_view (group_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), admin_group BOOLEAN, group_d_name VARCHAR(255), group_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), CONSTRAINT "users_groups_viewPK" PRIMARY KEY (group_no));

-- changeset pc-hp1:1635881616693-179
CREATE TABLE users_view (user_id INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), admin_user BOOLEAN, direct_mang INTEGER, direct_mang_d_name VARCHAR(255), direct_mang_f_name VARCHAR(255), group_no INTEGER, group_no_d_name VARCHAR(255), group_no_f_name VARCHAR(255), inactive BOOLEAN, inactive_date TIMESTAMP WITHOUT TIME ZONE, inactive_reason VARCHAR(255), inactive_user INTEGER, inactive_user_d_name VARCHAR(255), inactive_user_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), password VARCHAR(255), super_admin BOOLEAN, user_d_name VARCHAR(255), user_f_name VARCHAR(255), CONSTRAINT "users_viewPK" PRIMARY KEY (user_id));

-- changeset pc-hp1:1635881616693-180
CREATE TABLE zone_view (zone_no INTEGER NOT NULL, add_date TIMESTAMP WITHOUT TIME ZONE, add_user INTEGER, add_user_d_name VARCHAR(255), add_user_f_name VARCHAR(255), city_no INTEGER, city_no_d_name VARCHAR(255), city_no_f_name VARCHAR(255), country_no INTEGER, country_no_d_name VARCHAR(255), country_no_f_name VARCHAR(255), modify_date TIMESTAMP WITHOUT TIME ZONE, modify_user INTEGER, modify_user_d_name VARCHAR(255), modify_user_f_name VARCHAR(255), province_no INTEGER, province_no_d_name VARCHAR(255), province_no_f_name VARCHAR(255), region_no INTEGER, region_no_d_name VARCHAR(255), region_no_f_name VARCHAR(255), zone_d_name VARCHAR(255), zone_f_name VARCHAR(255), CONSTRAINT "zone_viewPK" PRIMARY KEY (zone_no));

-- changeset pc-hp1:1635881616693-181
CREATE UNIQUE INDEX "IX_accounts_privPK" ON accounts_priv(acc_curr, acc_no, user_id);

-- changeset pc-hp1:1635881616693-182
CREATE UNIQUE INDEX "IX_branches_privPK" ON branches_priv(branch_no, user_id);

-- changeset pc-hp1:1635881616693-183
CREATE UNIQUE INDEX "IX_flag_detailPK" ON flag_detail(flag_code, flag_value);

-- changeset pc-hp1:1635881616693-184
CREATE UNIQUE INDEX "IX_flag_privPK" ON flag_priv(flag_code, flag_value, user_id);

-- changeset pc-hp1:1635881616693-185
CREATE UNIQUE INDEX "IX_form_privilagePK" ON form_privilage(form_no, user_id);

-- changeset pc-hp1:1635881616693-186
CREATE UNIQUE INDEX "IX_labelsPK" ON labels(label_code, lang_no);

-- changeset pc-hp1:1635881616693-187
CREATE UNIQUE INDEX "IX_messagesPK" ON messages(lang_no, message_code);

-- changeset pc-hp1:1635881616693-188
ALTER TABLE accounts_group DROP CONSTRAINT "Add_user";

-- changeset pc-hp1:1635881616693-189
ALTER TABLE accounts_priv DROP CONSTRAINT "Add_user_fk";

-- changeset pc-hp1:1635881616693-190
ALTER TABLE users DROP CONSTRAINT "Inactive_user";

-- changeset pc-hp1:1635881616693-191
ALTER TABLE users DROP CONSTRAINT "Modify_user";

-- changeset pc-hp1:1635881616693-192
ALTER TABLE accounts_priv DROP CONSTRAINT "Modify_user_fk";

-- changeset pc-hp1:1635881616693-193
ALTER TABLE accounts_priv DROP CONSTRAINT acc_curr_fk;

-- changeset pc-hp1:1635881616693-194
ALTER TABLE chart_of_accounts DROP CONSTRAINT acc_group;

-- changeset pc-hp1:1635881616693-195
ALTER TABLE tax_types_dtl DROP CONSTRAINT acc_no;

-- changeset pc-hp1:1635881616693-196
ALTER TABLE cash_in_hand DROP CONSTRAINT acc_no_fk;

-- changeset pc-hp1:1635881616693-197
ALTER TABLE tax_types_dtl DROP CONSTRAINT account;

-- changeset pc-hp1:1635881616693-198
ALTER TABLE accounts_currency DROP CONSTRAINT accounts_no;

-- changeset pc-hp1:1635881616693-199
ALTER TABLE branches_priv DROP CONSTRAINT branch_fk;

-- changeset pc-hp1:1635881616693-200
ALTER TABLE cash_in_hand DROP CONSTRAINT branch_no_fk;

-- changeset pc-hp1:1635881616693-201
ALTER TABLE cash_in_hand_priv DROP CONSTRAINT cash_no_fk;

-- changeset pc-hp1:1635881616693-202
ALTER TABLE cost_center DROP CONSTRAINT cc_group;

-- changeset pc-hp1:1635881616693-203
ALTER TABLE branches DROP CONSTRAINT city;

-- changeset pc-hp1:1635881616693-204
ALTER TABLE branches DROP CONSTRAINT company;

-- changeset pc-hp1:1635881616693-205
ALTER TABLE company DROP CONSTRAINT "company-group";

-- changeset pc-hp1:1635881616693-206
ALTER TABLE cost_center_priv DROP CONSTRAINT cost_center;

-- changeset pc-hp1:1635881616693-207
ALTER TABLE branches DROP CONSTRAINT country;

-- changeset pc-hp1:1635881616693-208
ALTER TABLE company DROP CONSTRAINT country_fkey;

-- changeset pc-hp1:1635881616693-209
ALTER TABLE accounts_currency DROP CONSTRAINT currency;

-- changeset pc-hp1:1635881616693-210
ALTER TABLE cash_in_hand_dtl DROP CONSTRAINT currency_fk;

-- changeset pc-hp1:1635881616693-211
ALTER TABLE currency_history DROP CONSTRAINT currency_history_currency_code;

-- changeset pc-hp1:1635881616693-212
ALTER TABLE currency_values DROP CONSTRAINT currency_values_currency_code;

-- changeset pc-hp1:1635881616693-213
ALTER TABLE users DROP CONSTRAINT direct_mang;

-- changeset pc-hp1:1635881616693-214
ALTER TABLE city DROP CONSTRAINT fk_city_province;

-- changeset pc-hp1:1635881616693-215
ALTER TABLE country DROP CONSTRAINT fk_country_zone;

-- changeset pc-hp1:1635881616693-216
ALTER TABLE forms DROP CONSTRAINT fk_forms_modules;

-- changeset pc-hp1:1635881616693-217
ALTER TABLE form_privilage DROP CONSTRAINT fk_forms_priv;

-- changeset pc-hp1:1635881616693-218
ALTER TABLE labels DROP CONSTRAINT fk_labels_language;

-- changeset pc-hp1:1635881616693-219
ALTER TABLE messages DROP CONSTRAINT fk_messages_language;

-- changeset pc-hp1:1635881616693-220
ALTER TABLE province DROP CONSTRAINT fk_province_country;

-- changeset pc-hp1:1635881616693-221
ALTER TABLE users DROP CONSTRAINT fk_users_group;

-- changeset pc-hp1:1635881616693-222
ALTER TABLE form_privilage DROP CONSTRAINT fk_users_priv;

-- changeset pc-hp1:1635881616693-223
ALTER TABLE forms DROP CONSTRAINT flag_code;

-- changeset pc-hp1:1635881616693-224
ALTER TABLE flag_master DROP CONSTRAINT label_code;

-- changeset pc-hp1:1635881616693-225
ALTER TABLE doc_type DROP CONSTRAINT lang;

-- changeset pc-hp1:1635881616693-226
ALTER TABLE doc_type DROP CONSTRAINT module;

-- changeset pc-hp1:1635881616693-227
ALTER TABLE forms DROP CONSTRAINT parent_form;

-- changeset pc-hp1:1635881616693-228
ALTER TABLE branches DROP CONSTRAINT province;

-- changeset pc-hp1:1635881616693-229
ALTER TABLE tax_types_dtl DROP CONSTRAINT tax_no;

-- changeset pc-hp1:1635881616693-230
ALTER TABLE cost_center_priv DROP CONSTRAINT "user";

-- changeset pc-hp1:1635881616693-231
ALTER TABLE accounts_priv DROP CONSTRAINT user_fk;

-- changeset pc-hp1:1635881616693-232
ALTER TABLE currency DROP CONSTRAINT user_fkey;

-- changeset pc-hp1:1635881616693-233
ALTER TABLE cash_in_hand DROP CONSTRAINT user_no_fk;

-- changeset pc-hp1:1635881616693-234
ALTER TABLE country DROP CONSTRAINT userm_fkey;

-- changeset pc-hp1:1635881616693-235
ALTER TABLE chart_of_accounts DROP CONSTRAINT acc_d_name;

-- changeset pc-hp1:1635881616693-236
ALTER TABLE chart_of_accounts DROP CONSTRAINT acc_f_name;

-- changeset pc-hp1:1635881616693-237
ALTER TABLE accounts_group DROP CONSTRAINT group_dname;

-- changeset pc-hp1:1635881616693-238
ALTER TABLE accounts_group DROP CONSTRAINT group_fname;

-- changeset pc-hp1:1635881616693-239
DROP VIEW accounts_currency_view;

-- changeset pc-hp1:1635881616693-240
DROP VIEW accounts_group_view;

-- changeset pc-hp1:1635881616693-241
DROP VIEW branches_view;

-- changeset pc-hp1:1635881616693-242
DROP VIEW chart_of_accounts_view;

-- changeset pc-hp1:1635881616693-243
DROP VIEW city_view;

-- changeset pc-hp1:1635881616693-244
DROP VIEW company_groups_view;

-- changeset pc-hp1:1635881616693-245
DROP VIEW company_view;

-- changeset pc-hp1:1635881616693-246
DROP VIEW cost_center_group_view;

-- changeset pc-hp1:1635881616693-247
DROP VIEW cost_center_view;

-- changeset pc-hp1:1635881616693-248
DROP VIEW country_view;

-- changeset pc-hp1:1635881616693-249
DROP VIEW currency_history_view;

-- changeset pc-hp1:1635881616693-250
DROP VIEW currency_values_view;

-- changeset pc-hp1:1635881616693-251
DROP VIEW currency_view;

-- changeset pc-hp1:1635881616693-252
DROP VIEW emp_info_view;

-- changeset pc-hp1:1635881616693-253
DROP VIEW flag_detail_main_tree;

-- changeset pc-hp1:1635881616693-254
DROP VIEW flag_detail_view;

-- changeset pc-hp1:1635881616693-255
DROP VIEW flag_master_view;

-- changeset pc-hp1:1635881616693-256
DROP VIEW flag_priv_view;

-- changeset pc-hp1:1635881616693-257
DROP VIEW form_privilage_view;

-- changeset pc-hp1:1635881616693-258
DROP VIEW forms_view;

-- changeset pc-hp1:1635881616693-259
DROP VIEW labels_view;

-- changeset pc-hp1:1635881616693-260
DROP VIEW language_view;

-- changeset pc-hp1:1635881616693-261
DROP VIEW messages_view;

-- changeset pc-hp1:1635881616693-262
DROP VIEW modules_view;

-- changeset pc-hp1:1635881616693-263
DROP VIEW province_view;

-- changeset pc-hp1:1635881616693-264
DROP VIEW region_view;

-- changeset pc-hp1:1635881616693-265
DROP VIEW users_groups_view;

-- changeset pc-hp1:1635881616693-266
DROP VIEW users_view;

-- changeset pc-hp1:1635881616693-267
DROP VIEW zone_view;

-- changeset pc-hp1:1635881616693-268
DROP TABLE cash_in_hand;

-- changeset pc-hp1:1635881616693-269
DROP TABLE cash_in_hand_dtl;

-- changeset pc-hp1:1635881616693-270
DROP TABLE cash_in_hand_priv;

-- changeset pc-hp1:1635881616693-271
DROP TABLE cost_center_priv;

-- changeset pc-hp1:1635881616693-272
DROP TABLE doc_type;

-- changeset pc-hp1:1635881616693-273
DROP TABLE tax_types_dtl;

-- changeset pc-hp1:1635881616693-274
DROP TABLE tax_types_mst;

-- changeset pc-hp1:1635881616693-275
DROP TABLE user_priv;

-- changeset pc-hp1:1635881616693-1
ALTER TABLE chart_of_accounts ALTER COLUMN  acc_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-2
ALTER TABLE accounts_currency ALTER COLUMN  active DROP NOT NULL;

-- changeset pc-hp1:1635881616693-3
ALTER TABLE flag_detail ALTER COLUMN  active DROP NOT NULL;

-- changeset pc-hp1:1635881616693-4
ALTER TABLE forms ALTER COLUMN  active DROP NOT NULL;

-- changeset pc-hp1:1635881616693-5
ALTER TABLE language ALTER COLUMN  active DROP NOT NULL;

-- changeset pc-hp1:1635881616693-6
ALTER TABLE modules ALTER COLUMN  active DROP NOT NULL;

-- changeset pc-hp1:1635881616693-7
ALTER TABLE "Company" ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-8
ALTER TABLE accounts_currency ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-9
ALTER TABLE accounts_group ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-10
ALTER TABLE accounts_priv ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-11
ALTER TABLE branches ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-12
ALTER TABLE branches_priv ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-13
ALTER TABLE chart_of_accounts ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-14
ALTER TABLE city ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-15
ALTER TABLE company_groups ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-16
ALTER TABLE cost_center ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-17
ALTER TABLE cost_center_group ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-18
ALTER TABLE currency ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-19
ALTER TABLE emp_info ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-20
ALTER TABLE flag_priv ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-21
ALTER TABLE form_privilage ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-22
ALTER TABLE labels ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-23
ALTER TABLE messages ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-24
ALTER TABLE users ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-25
ALTER TABLE users_groups ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-26
ALTER TABLE zone ALTER COLUMN  add_date DROP NOT NULL;

-- changeset pc-hp1:1635881616693-27
ALTER TABLE accounts_priv ALTER COLUMN  add_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-28
ALTER TABLE branches_priv ALTER COLUMN  add_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-29
ALTER TABLE flag_priv ALTER COLUMN  add_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-30
ALTER TABLE form_privilage ALTER COLUMN  add_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-31
ALTER TABLE "Company" ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-32
ALTER TABLE accounts_currency ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-33
ALTER TABLE accounts_group ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-34
ALTER TABLE accounts_priv ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-35
ALTER TABLE branches ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-36
ALTER TABLE branches_priv ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-37
ALTER TABLE chart_of_accounts ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-38
ALTER TABLE city ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-39
ALTER TABLE company_groups ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-40
ALTER TABLE cost_center ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-41
ALTER TABLE cost_center_group ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-42
ALTER TABLE currency ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-43
ALTER TABLE currency_values ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-44
ALTER TABLE emp_info ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-45
ALTER TABLE flag_priv ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-46
ALTER TABLE form_privilage ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-47
ALTER TABLE labels ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-48
ALTER TABLE messages ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-49
ALTER TABLE users ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-50
ALTER TABLE users_groups ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-51
ALTER TABLE zone ALTER COLUMN  add_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-52
ALTER TABLE users_groups ALTER COLUMN  admin_group DROP NOT NULL;

-- changeset pc-hp1:1635881616693-53
ALTER TABLE users ALTER COLUMN  admin_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-54
ALTER TABLE form_privilage ALTER COLUMN  audit_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-55
ALTER TABLE branches ALTER COLUMN  branch_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-56
ALTER TABLE chart_of_accounts ALTER COLUMN  bs DROP NOT NULL;

-- changeset pc-hp1:1635881616693-57
ALTER TABLE cost_center ALTER COLUMN  cc_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-58
ALTER TABLE acc_para ALTER COLUMN  cc_post DROP NOT NULL;

-- changeset pc-hp1:1635881616693-59
ALTER TABLE chart_of_accounts ALTER COLUMN  cc_post DROP NOT NULL;

-- changeset pc-hp1:1635881616693-60
ALTER TABLE city ALTER COLUMN  city_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-61
ALTER TABLE zone ALTER COLUMN  city_no DROP NOT NULL;

-- changeset pc-hp1:1635881616693-62
ALTER TABLE "Company" ALTER COLUMN  company_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-63
ALTER TABLE "Company" ALTER COLUMN  company_group DROP NOT NULL;

-- changeset pc-hp1:1635881616693-64
ALTER TABLE branches ALTER COLUMN  company_no DROP NOT NULL;

-- changeset pc-hp1:1635881616693-65
ALTER TABLE country ALTER COLUMN  country_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-66
ALTER TABLE province ALTER COLUMN  country_no DROP NOT NULL;

-- changeset pc-hp1:1635881616693-67
ALTER TABLE currency ALTER COLUMN  currency_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-68
ALTER TABLE flag_priv ALTER COLUMN  delete_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-69
ALTER TABLE form_privilage ALTER COLUMN  delete_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-70
ALTER TABLE users ALTER COLUMN  direct_mang DROP NOT NULL;

-- changeset pc-hp1:1635881616693-71
ALTER TABLE chart_of_accounts ALTER COLUMN  dr DROP NOT NULL;

-- changeset pc-hp1:1635881616693-72
ALTER TABLE emp_info ALTER COLUMN  emp_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-73
ALTER TABLE currency ALTER COLUMN  exchange_rate DROP NOT NULL;

-- changeset pc-hp1:1635881616693-74
ALTER TABLE currency_history ALTER COLUMN  exchange_rate DROP NOT NULL;

-- changeset pc-hp1:1635881616693-75
ALTER TABLE flag_detail ALTER COLUMN  flag_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-76
ALTER TABLE flag_detail ALTER COLUMN  flag_sr DROP NOT NULL;

-- changeset pc-hp1:1635881616693-77
ALTER TABLE forms ALTER COLUMN  form_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-78
ALTER TABLE forms ALTER COLUMN  form_order DROP NOT NULL;

-- changeset pc-hp1:1635881616693-79
ALTER TABLE currency ALTER COLUMN  fraction_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-80
ALTER TABLE accounts_group ALTER COLUMN  group_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-81
ALTER TABLE company_groups ALTER COLUMN  group_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-82
ALTER TABLE cost_center_group ALTER COLUMN  group_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-83
ALTER TABLE users_groups ALTER COLUMN  group_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-84
ALTER TABLE chart_of_accounts ALTER COLUMN  inactive DROP NOT NULL;

-- changeset pc-hp1:1635881616693-85
ALTER TABLE cost_center ALTER COLUMN  inactive DROP NOT NULL;

-- changeset pc-hp1:1635881616693-86
ALTER TABLE emp_info ALTER COLUMN  inactive DROP NOT NULL;

-- changeset pc-hp1:1635881616693-87
ALTER TABLE users ALTER COLUMN  inactive DROP NOT NULL;

-- changeset pc-hp1:1635881616693-88
ALTER TABLE form_privilage ALTER COLUMN  include_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-89
ALTER TABLE flag_detail ALTER COLUMN  label_code DROP NOT NULL;

-- changeset pc-hp1:1635881616693-90
ALTER TABLE flag_master ALTER COLUMN  label_code DROP NOT NULL;

-- changeset pc-hp1:1635881616693-91
ALTER TABLE labels ALTER COLUMN  label_desc DROP NOT NULL;

-- changeset pc-hp1:1635881616693-92
ALTER TABLE language ALTER COLUMN  lang_dfl DROP NOT NULL;

-- changeset pc-hp1:1635881616693-93
ALTER TABLE language ALTER COLUMN  lang_dir DROP NOT NULL;

-- changeset pc-hp1:1635881616693-94
ALTER TABLE language ALTER COLUMN  lang_ext DROP NOT NULL;

-- changeset pc-hp1:1635881616693-95
ALTER TABLE language ALTER COLUMN  lang_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-96
ALTER TABLE flag_detail ALTER COLUMN  lang_no DROP NOT NULL;

-- changeset pc-hp1:1635881616693-97
ALTER TABLE flag_master ALTER COLUMN  lang_no DROP NOT NULL;

-- changeset pc-hp1:1635881616693-98
ALTER TABLE chart_of_accounts ALTER COLUMN  level DROP NOT NULL;

-- changeset pc-hp1:1635881616693-99
ALTER TABLE currency ALTER COLUMN  local_currency DROP NOT NULL;

-- changeset pc-hp1:1635881616693-100
ALTER TABLE forms ALTER COLUMN  main DROP NOT NULL;

-- changeset pc-hp1:1635881616693-101
ALTER TABLE messages ALTER COLUMN  message_desc DROP NOT NULL;

-- changeset pc-hp1:1635881616693-102
ALTER TABLE flag_priv ALTER COLUMN  modify_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-103
ALTER TABLE form_privilage ALTER COLUMN  modify_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-104
ALTER TABLE currency_history ALTER COLUMN  modify_user DROP NOT NULL;

-- changeset pc-hp1:1635881616693-105
ALTER TABLE modules ALTER COLUMN  module_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-106
ALTER TABLE forms ALTER COLUMN  module_no DROP NOT NULL;

-- changeset pc-hp1:1635881616693-107
ALTER TABLE modules ALTER COLUMN  order_no DROP NOT NULL;

-- changeset pc-hp1:1635881616693-108
ALTER TABLE chart_of_accounts ALTER COLUMN  parent_acc DROP NOT NULL;

-- changeset pc-hp1:1635881616693-109
ALTER TABLE cost_center ALTER COLUMN  parent_cc DROP NOT NULL;

-- changeset pc-hp1:1635881616693-110
ALTER TABLE forms ALTER COLUMN  parent_form DROP NOT NULL;

-- changeset pc-hp1:1635881616693-111
ALTER TABLE users ALTER COLUMN  password DROP NOT NULL;

-- changeset pc-hp1:1635881616693-112
ALTER TABLE form_privilage ALTER COLUMN  post_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-113
ALTER TABLE flag_priv ALTER COLUMN  print_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-114
ALTER TABLE form_privilage ALTER COLUMN  print_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-115
ALTER TABLE province ALTER COLUMN  province_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-116
ALTER TABLE city ALTER COLUMN  province_no DROP NOT NULL;

-- changeset pc-hp1:1635881616693-117
ALTER TABLE region ALTER COLUMN  region_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-118
ALTER TABLE country ALTER COLUMN  region_no DROP NOT NULL;

-- changeset pc-hp1:1635881616693-119
ALTER TABLE language ALTER COLUMN  report_ext DROP NOT NULL;

-- changeset pc-hp1:1635881616693-120
ALTER TABLE city ALTER COLUMN  shortcut DROP NOT NULL;

-- changeset pc-hp1:1635881616693-121
ALTER TABLE country ALTER COLUMN  shortcut DROP NOT NULL;

-- changeset pc-hp1:1635881616693-122
ALTER TABLE modules ALTER COLUMN  shortcut DROP NOT NULL;

-- changeset pc-hp1:1635881616693-123
ALTER TABLE province ALTER COLUMN  shortcut DROP NOT NULL;

-- changeset pc-hp1:1635881616693-124
ALTER TABLE region ALTER COLUMN  shortcut DROP NOT NULL;

-- changeset pc-hp1:1635881616693-125
ALTER TABLE "Company" ALTER COLUMN  shortcut_d DROP NOT NULL;

-- changeset pc-hp1:1635881616693-126
ALTER TABLE branches ALTER COLUMN  shortcut_d DROP NOT NULL;

-- changeset pc-hp1:1635881616693-127
ALTER TABLE chart_of_accounts ALTER COLUMN  sub DROP NOT NULL;

-- changeset pc-hp1:1635881616693-128
ALTER TABLE cost_center ALTER COLUMN  sub DROP NOT NULL;

-- changeset pc-hp1:1635881616693-129
ALTER TABLE acc_para ALTER COLUMN  sub_acc_lvl DROP NOT NULL;

-- changeset pc-hp1:1635881616693-130
ALTER TABLE users ALTER COLUMN  super_admin DROP NOT NULL;

-- changeset pc-hp1:1635881616693-131
ALTER TABLE accounts_currency ALTER COLUMN  used DROP NOT NULL;

-- changeset pc-hp1:1635881616693-132
ALTER TABLE users ALTER COLUMN  user_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-133
ALTER TABLE accounts_priv ALTER COLUMN  view_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-134
ALTER TABLE branches_priv ALTER COLUMN  view_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-135
ALTER TABLE flag_priv ALTER COLUMN  view_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-136
ALTER TABLE form_privilage ALTER COLUMN  view_priv DROP NOT NULL;

-- changeset pc-hp1:1635881616693-137
ALTER TABLE zone ALTER COLUMN  zone_d_name DROP NOT NULL;

-- changeset pc-hp1:1635881616693-138
DO $$ DECLARE constraint_name varchar;
BEGIN
  SELECT tc.CONSTRAINT_NAME into strict constraint_name
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
    WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
      AND TABLE_NAME = 'accounts_priv' AND TABLE_SCHEMA = 'public';
    EXECUTE 'alter table public.accounts_priv drop constraint ' || constraint_name;
END $$;;

-- changeset pc-hp1:1635881616693-139
ALTER TABLE accounts_priv ADD CONSTRAINT "accounts_privPK" PRIMARY KEY (acc_curr, acc_no, user_id);

-- changeset pc-hp1:1635881616693-140
DO $$ DECLARE constraint_name varchar;
BEGIN
  SELECT tc.CONSTRAINT_NAME into strict constraint_name
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
    WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
      AND TABLE_NAME = 'branches_priv' AND TABLE_SCHEMA = 'public';
    EXECUTE 'alter table public.branches_priv drop constraint ' || constraint_name;
END $$;;

-- changeset pc-hp1:1635881616693-141
ALTER TABLE branches_priv ADD CONSTRAINT "branches_privPK" PRIMARY KEY (branch_no, user_id);

-- changeset pc-hp1:1635881616693-142
DO $$ DECLARE constraint_name varchar;
BEGIN
  SELECT tc.CONSTRAINT_NAME into strict constraint_name
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
    WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
      AND TABLE_NAME = 'flag_detail' AND TABLE_SCHEMA = 'public';
    EXECUTE 'alter table public.flag_detail drop constraint ' || constraint_name;
END $$;;

-- changeset pc-hp1:1635881616693-143
ALTER TABLE flag_detail ADD CONSTRAINT "flag_detailPK" PRIMARY KEY (flag_code, flag_value);

-- changeset pc-hp1:1635881616693-144
DO $$ DECLARE constraint_name varchar;
BEGIN
  SELECT tc.CONSTRAINT_NAME into strict constraint_name
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
    WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
      AND TABLE_NAME = 'flag_priv' AND TABLE_SCHEMA = 'public';
    EXECUTE 'alter table public.flag_priv drop constraint ' || constraint_name;
END $$;;

-- changeset pc-hp1:1635881616693-145
ALTER TABLE flag_priv ADD CONSTRAINT "flag_privPK" PRIMARY KEY (flag_code, flag_value, user_id);

-- changeset pc-hp1:1635881616693-146
DO $$ DECLARE constraint_name varchar;
BEGIN
  SELECT tc.CONSTRAINT_NAME into strict constraint_name
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
    WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
      AND TABLE_NAME = 'form_privilage' AND TABLE_SCHEMA = 'public';
    EXECUTE 'alter table public.form_privilage drop constraint ' || constraint_name;
END $$;;

-- changeset pc-hp1:1635881616693-147
ALTER TABLE form_privilage ADD CONSTRAINT "form_privilagePK" PRIMARY KEY (form_no, user_id);

-- changeset pc-hp1:1635881616693-148
DO $$ DECLARE constraint_name varchar;
BEGIN
  SELECT tc.CONSTRAINT_NAME into strict constraint_name
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
    WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
      AND TABLE_NAME = 'labels' AND TABLE_SCHEMA = 'public';
    EXECUTE 'alter table public.labels drop constraint ' || constraint_name;
END $$;;

-- changeset pc-hp1:1635881616693-149
ALTER TABLE labels ADD CONSTRAINT "labelsPK" PRIMARY KEY (label_code, lang_no);

-- changeset pc-hp1:1635881616693-150
DO $$ DECLARE constraint_name varchar;
BEGIN
  SELECT tc.CONSTRAINT_NAME into strict constraint_name
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
    WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
      AND TABLE_NAME = 'messages' AND TABLE_SCHEMA = 'public';
    EXECUTE 'alter table public.messages drop constraint ' || constraint_name;
END $$;;

-- changeset pc-hp1:1635881616693-151
ALTER TABLE messages ADD CONSTRAINT "messagesPK" PRIMARY KEY (lang_no, message_code);

