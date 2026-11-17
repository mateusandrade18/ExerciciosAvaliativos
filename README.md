<img width="404" height="745" alt="image" src="https://github.com/user-attachments/assets/4018d323-9e42-4e0d-b5f2-848f551bca48" />
<img width="1040" height="733" alt="image" src="https://github.com/user-attachments/assets/262318e3-4ad5-47e6-b110-f19fe314a938" />
<img width="1467" height="379" alt="image" src="https://github.com/user-attachments/assets/3aa8d0a2-05fd-4a66-afdf-c7852b83ece4" />

<img width="939" height="540" alt="image" src="https://github.com/user-attachments/assets/b964d4c3-50bf-4fec-9f22-62937c0223f1" />
<img width="939" height="540" alt="image" src="https://github.com/user-attachments/assets/645063c5-4117-48bf-8429-66e0d8b6c6b7" 
  <img width="1637" height="452" alt="image" src="https://github.com/user-attachments/assets/ad19cddf-ce1a-4eec-a8ae-28c433b5d94f" />
  <img width="1028" height="463" alt="image" src="https://github.com/user-attachments/assets/a94830d1-3592-40bb-9f3c-27397f4ac1ff" />

  Herença - Foi utilizada quando transformei a classe Robo em uma classe abstrata e criei as três subclasses: RoboOfensivo, RoboDefensivo e RoboEquilibrado. Essas classes utilizam extend para herdar os atributos e métodos da classe principal. Também utilizei super` nos construtores para aproveitar as validações e inicializações que já existiam na classe Robo, sem precisar repetir o código.

Polimorfismo - Foi aplicado nos métodos calcularAtaque e reduzirDano, que possuem comportamentos diferentes dependendo do tipo de robô. O ofensivo acrescenta 5 ao ataque, o defensivo reduz em 3 o dano recebido e o equilibrado mantém as regras normais. Para isso, utilizei @Override nas subclasses. Dessa forma, o combate consegue executar o comportamento correto de cada robô sem precisar verificar seu tipo.

Encapsulamento - Foi mantido na classe Robo, onde todos os atributos continuam privados private. Para consultar as informações, utilizei os métodos get, que já existiam no código original. Além disso, a energia, os pontos e os resultados dos combates são alterados por métodos específicos, como receberDano, recuperarEnergia e registrarVitoria, mantendo as regras do sistema e evitando alterações diretas nos atributos.

  




