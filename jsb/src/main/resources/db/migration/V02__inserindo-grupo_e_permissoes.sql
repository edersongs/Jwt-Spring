-- INSERINDO GRUPO

insert into tgrupo values(1, 'ADMINISTRADOR');

insert into tgrupo values(2, 'DESENVOLVEDOR');


-- INSERINDO PERMISSÃO

insert into tpermissao values(1, 'CADASTRAR_USUARIO', 'Cadastrar Usuario no Sistema');

insert into tpermissao values(2, 'PESQUISAR_USUARIO', 'Pesquisar Usuarios no Sistema');



-- ASSOCIANDO GRUPO E PERMISSÃO

insert into tgrupopermissao values(1,1);

insert into tgrupopermissao values(1,2);

insert into tgrupopermissao values(2,2)