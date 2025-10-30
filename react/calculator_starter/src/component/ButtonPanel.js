import Button from "./Button";
import React from "react";
import PropTypes from "prop-types";

import "./ButtonPanel.css";

export default class ButtonPanel extends React.Component {
  render() {
    return <div className="component-button-panel">
      <div>
        
          <Button onClick={()=>this.props.clickHandler("AC")}>AC</Button>
        
          <Button onClick={()=>this.props.clickHandler("+/-")}>+/-</Button>
        
          <Button onClick={()=>this.props.clickHandler("%")}>%</Button>

          <Button onClick={()=>this.props.clickHandler("÷")} orange={true}>&divide;</Button>

      </div>
      <div>
      
          <Button onClick={()=>this.props.clickHandler("7")}>7</Button>
        
      
          <Button onClick={()=>this.props.clickHandler("8")}>8</Button>
  
   
          <Button onClick={()=>this.props.clickHandler("9")}>9</Button>
  
  
          <Button onClick={()=>this.props.clickHandler("x")} orange={true}>x</Button>
    
      </div>
      <div>

          <Button onClick={()=>this.props.clickHandler("4")}>4</Button>


          <Button onClick={()=>this.props.clickHandler("5")}>5</Button>

          <Button onClick={()=>this.props.clickHandler("6")}>6</Button>
 
          <Button onClick={()=>this.props.clickHandler("-")} orange={true}>-</Button>

      </div>
      <div>

          <Button onClick={()=>this.props.clickHandler("1")}>1</Button>

          <Button onClick={()=>this.props.clickHandler("2")}>2</Button>

          <Button onClick={()=>this.props.clickHandler("3")}>3</Button>

          <Button onClick={()=>this.props.clickHandler("+")} orange={true}>+</Button>

      </div>
      <div>

          <Button onClick={()=>this.props.clickHandler("0")} wide={true}>0</Button>
     
          <Button onClick={()=>this.props.clickHandler(".")}>.</Button>
 
          <Button onClick={()=>this.props.clickHandler("=")} orange={true}>=</Button>

      </div>
    </div>;
  }
}

