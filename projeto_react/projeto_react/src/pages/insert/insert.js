import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import './insert.css';

class CriarUsuario extends Component {
    constructor() {
        super();

        this.state = {
            usuario: {
                nome: "",
                matricula: 0,
                ativo: "true"
            },
            redirect: false
        }
    }

    render() {
        const {redirect} = this.state;
        if(redirect) {
            return <Redirect to="/" />
        } else {
            return (
                <form onSubmit={this.sendSubmit}>
                    <fieldset>
                    <legend>Adicionar Usuário</legend>
                    <div className="usuario-insert">
                        <label htmlFor="nome">Nome</label>
                        <br/>
                        <input type="text" id="nome" placeholder="Informe seu nome" required
                         minLength="3" maxLength="100" value={this.state.usuario.nome}
                         onChange={this.inputChange} />
                    </div>

                    <div className="usuario-insert">
                        <label htmlFor="matricula">Matricula</label>
                        <br/>
                        <input type="number" id="matricula" placeholder="Informe sua matricula" required
                         minLength="1" maxLength="9999" value={this.state.usuario.matricula}
                         onChange={this.inputChange} />                         
                    </div>
                    <div className="usuario-insert">
                        <label>
                            <input type="radio" name="ativo" value="true" 
                            checked={this.state.usuario.ativo === "true"}
                            onChange={this.inputChange} />
                            Ativo
                        </label>
                        <label>
                            <input type="radio" name="inativo" value="false" 
                            checked={this.state.usuario.ativo === "false"}
                            onChange={this.inputChange} />
                            Inativo
                        </label>
                    </div>
                    <button type="submit">Enviar</button>
                    </fieldset>
                </form>
            )
        }   
    }
    inputChange = event => {
        const target = event.target;
        const name = target.name;
        const value = target.value;

        this.setState(prevState => ({
            usuario: {...prevState.usuario, [name]: value}
        }));
    };

    sendSubmit = event => {
        fetch("http://localhost:3001/sistema/usuarios", {
            method:"post",
            body: JSON.stringify(this.state.usuario),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(data => {
            if(data.ok) {
                this.setState({redirect: true});
            }
        })
        event.preventDefault();
    }
}

export default CriarUsuario;